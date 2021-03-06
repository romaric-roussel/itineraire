package com.example.romi.testsig;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.log;
import static java.lang.Math.sin;


public class MainActivity extends AppCompatActivity   {

    private Spinner sp_deb,sp_fin;
    private TextView tv;
    private Button btn_recherche;


    private List<GeoPoint> pointList;
    private List<GeoArc> arcList;
    private List<GeoArc> copyArcList;
    private LinkedList<GeoPoint> path;

    private Dao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp_deb = findViewById(R.id.sp_depart);
        sp_fin = findViewById(R.id.sp_arrive);
        btn_recherche = findViewById(R.id.btn_recherche);
        Button btn_largeur=findViewById(R.id.btn_largeur);
        tv = findViewById(R.id.textView);



        dao = new Dao(this);
        dao.open();

        pointList = dao.getAllPoint();
        arcList = dao.getAllArc();
        List<GeoArc> arcReverseList = new ArrayList<>();
        for (int i =0;i<arcList.size();i++){
           GeoArc arc = arcList.get(i);
           GeoArc arcReverse = new GeoArc(arc.getGeo_arc_id(), arc.getGeo_arc_fin(), arc.getGeo_arc_deb(), arc.getGeo_arc_temps(), arc.getGeo_arc_distance(), arc.getGeo_arc_sens());
           arcReverseList.add(arcReverse);

        }
        arcList.addAll(arcReverseList);




        ArrayAdapter<GeoPoint> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,pointList);
        sp_deb.setAdapter(adapter);
        sp_fin.setAdapter(adapter);
        sp_deb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       sp_fin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });


        btn_recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DemandeDePermission();
            }
        });
        btn_largeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Graphe graph = new Graphe(pointList, arcList);
                ParcoursLargeur parcours= new ParcoursLargeur(graph);
                List<Integer> largeur = parcours.execute((GeoPoint)sp_deb.getSelectedItem());
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0;i<largeur.size();i++) {
                    if(i==largeur.size()-1){
                        stringBuilder.append(largeur.get(i));
                    }else {
                        stringBuilder.append(largeur.get(i)).append("=>");
                    }

                }
                tv.setText(stringBuilder);
            }
        });

    }



    private  void DemandeDePermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                {
                   explain();
                }
                else
                {
                    askForPermission();
                }
            }
        }
        else
        {
            Graphe graph = new Graphe(pointList, arcList);
            execDjisktra(graph);



            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"export.kml");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {

                if(path!= null){
                    fileWriter.write("<?xml version='1.0' encoding='UTF-8'?>\n");
                    fileWriter.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n");
                    fileWriter.write("<Document>\n" + "<Folder>\n" + "<name>Arret de bus</name>\n");
                    fileWriter.write("<Style id=\"placemark-red\">\n" +
                            "        <IconStyle>\n" +
                            "          <Icon>\n" +
                            "            <href>https://static.thenounproject.com/png/120114-200.png</href>\n" +
                            "          </Icon>\n" +
                            "        </IconStyle>\n" +
                            "      </Style>");

                    for (GeoPoint point : path) {
                        fileWriter.write("<Placemark>\n");
                        fileWriter.write( "<styleUrl>#placemark-red</styleUrl>\n");
                        fileWriter.write("<name>" +point.getGeo_poi_nom()+ "</name>\n");
                        fileWriter.write("<styleUrl>#placemark-red</styleUrl>\n");
                        fileWriter.write("<Point>\n" +
                                "<coordinates>"+ point.getGeo_poi_longitude() +"," + point.getGeo_poi_latitude()
                                +"</coordinates>\n" +
                                "</Point>\n");
                        fileWriter.write("</Placemark>\n");

                    }
                    fileWriter.write( "<Placemark>\n");
                    fileWriter.write( "<styleUrl>#placemark-red</styleUrl>\n");

                    fileWriter.write( "<name>Itineraire</name>\n" + "<LineString>\n" + "<coordinates>\n");
                    for (GeoPoint point : path) {
                        fileWriter.write(point.getGeo_poi_longitude() +"," + point.getGeo_poi_latitude()+"\n");
                    }

                    fileWriter.write("<styleUrl>#placemark-red</styleUrl>\n");
                    fileWriter.write("</coordinates>\n" + "</LineString>\n"+"</Placemark>\n");
                    fileWriter.write("</Folder>\n" + "</Document>\n"+"</kml>");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void execDjisktra(Graphe graphe){
        Djikstra djikstra = new Djikstra(graphe);
        djikstra.execute((GeoPoint)sp_deb.getSelectedItem());
        path = djikstra.getPath((GeoPoint)sp_fin.getSelectedItem());
        if(path!= null){
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0;i<path.size();i++) {
                if(i==path.size()-1){
                    stringBuilder.append(path.get(i).getGeo_poi_nom()).append("(").append(path.get(i).getGeo_poi_partition()).append(")");

                }else {
                    stringBuilder.append(path.get(i).getGeo_poi_nom()).append("(").append(path.get(i).getGeo_poi_partition()).append(")").append("=>").append("\n");
                }
            }
            stringBuilder.append("Distance total : ").append(djikstra.getDistance((GeoPoint)sp_fin.getSelectedItem())).append(" KM");
            tv.setText(stringBuilder);

            } else{
                Snackbar.make(findViewById(android.R.id.content), "Aucun chemin", Snackbar.LENGTH_SHORT).show();}
        }





    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if(requestCode == 2)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Graphe graph = new Graphe(pointList, arcList);
                execDjisktra(graph);



                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"export.kml");
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {

                    if(path!= null){
                        fileWriter.write("<?xml version='1.0' encoding='UTF-8'?>\n");
                        fileWriter.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n");
                        fileWriter.write("<Document>\n" + "<Folder>\n" + "<name>Arret de bus</name>\n");

                        for (GeoPoint point : path) {
                            fileWriter.write("<Placemark>\n");
                            fileWriter.write("<name>" +point.getGeo_poi_nom()+ "</name>\n");
                            fileWriter.write("<Point>\n" +
                                    "<coordinates>"+ point.getGeo_poi_longitude() +"," + point.getGeo_poi_latitude()
                                    +"</coordinates>\n" +
                                    "</Point>\n");
                            fileWriter.write("</Placemark>\n");

                        }
                        fileWriter.write( "<Placemark>\n");
                        fileWriter.write( "<name>Itineraire</name>\n" + "<LineString>\n" + "<coordinates>\n");
                        for (GeoPoint point : path) {
                            fileWriter.write(point.getGeo_poi_longitude() +"," + point.getGeo_poi_latitude()+"\n");
                        }
                        fileWriter.write("</coordinates>\n" + "</LineString>\n"+"</Placemark>\n");
                        fileWriter.write("</Folder>\n" + "</Document>\n"+"</kml>");
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(shouldShowRequestPermissionRationale(permissions[0]) == false)
                {
                    displayOptions();
                }
                else
                {
                    explain();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    private void displayOptions()
    {
        Snackbar.make(findViewById(android.R.id.content), "Vous avez désactivé la permission", Snackbar.LENGTH_LONG).setAction("Paramètres", new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                final Uri uri = Uri.fromParts("package",MainActivity.this.getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        }).show();
    }
    private void askForPermission()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, 2);
        }
    }
    private void explain()
    {
        Snackbar.make(findViewById(android.R.id.content), "Cette permission est nécessaire pour ecrire le fichier kml", Snackbar.LENGTH_LONG).setAction("Activer", new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                askForPermission();
            }
        }).show();
    }



}
