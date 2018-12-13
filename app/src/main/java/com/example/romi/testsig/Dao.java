package com.example.romi.testsig;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.log;
import static java.lang.Math.sin;

/**
 * Created by lance on 06/02/2017.
 */

public class Dao {

    private SQLiteDatabase database;
    private Context c;
    private GestionBd dbHelper;
    private GeoPoint point;
    private GeoArc arc;


    public Dao(Context context) {
        c = context;
        dbHelper = new GestionBd(context, C.Database.NAME, null, C.Database.VERSION);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    private static List<Double> convertDegToLamb(double lat,double lon){
        List<Double> lamb = new ArrayList<>();
        double n=0.7289686274;
        double C=11745793.39;
        double e=0.08248325676;
        double Xs=600000;
        double Ys=8199695.768;

        double GAMMA0 =(3600*2)+(60*20)+14.025;
        GAMMA0 = GAMMA0/(180*3600)*Math.PI;
        double lati =lat/180*Math.PI;
        double longi =lon/180*Math.PI;
        double L=0.5* log((1+sin(lati))/(1-sin(lati)))-e/2*log((1+e*sin(lati))/(1-e*sin(lati)));
        double R=C*Math.exp(-n*L);
        double GAMMA=n*(longi-GAMMA0);

        double Lx=Xs+(R*sin(GAMMA));
        double  Ly=Ys-(R*cos(GAMMA));

        lamb.add(Lx);
        lamb.add(Ly);

        return lamb;
    }
    public static double distancePoint(double xa,double ya,double xb,double yb){

        double distance = Math.sqrt(Math.pow(xb-xa,2)+ Math.pow(yb-ya,2));
        return  distance;
    }



    public List<GeoPoint> getAllPoint() {
        List<GeoPoint> points = new ArrayList<GeoPoint>();
        // creer un curseur qui recupère nos resultat
        Cursor cursor = database.rawQuery("SELECT * FROM " + C.Table.POINT, null);
        // si le curseur n'est pas vide
        if (cursor != null) {
            //déplace le curseur a la première ligne
            if (cursor.moveToFirst()) {
                do {

                    point = new GeoPoint();
                    int id = cursor.getInt(cursor.getColumnIndex(C.FieldPOINT.ID));
                    point.setGeo_poi_id(id);
                    double latitude = cursor.getDouble(cursor.getColumnIndex(C.FieldPOINT.LATITUDE));
                    point.setGeo_poi_latitude(latitude);
                    double longitude = cursor.getDouble(cursor.getColumnIndex(C.FieldPOINT.LONGTITUDE));
                    point.setGeo_poi_longitude(longitude);
                    String nom = cursor.getString(cursor.getColumnIndex(C.FieldPOINT.NOM));
                    point.setGeo_poi_nom(nom);
                    int partition = cursor.getInt(cursor.getColumnIndex(C.FieldPOINT.PARTITION));
                    point.setGeo_poi_partition(partition);
                    points.add(point);

                } while (cursor.moveToNext());
            }
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return points;
    }

    public List<GeoArc> getAllArc() {
        List<GeoPoint> points = getAllPoint();
        List<GeoArc> arcs = new ArrayList<>();

        // creer un curseur qui recupère nos resultat
        Cursor cursor = database.rawQuery("SELECT * FROM " + C.Table.ARC, null);
        // si le curseur n'est pas vide
        if (cursor != null) {
            //déplace le curseur a la première ligne
            if (cursor.moveToFirst()) {
                do {

                    arc = new GeoArc();
                    int id = cursor.getInt(cursor.getColumnIndex(C.FieldARC.ID));
                    arc.setGeo_arc_id(id);
                    int debut = cursor.getInt(cursor.getColumnIndex(C.FieldARC.DEB));
                    int fin = cursor.getInt(cursor.getColumnIndex(C.FieldARC.FIN));
                    for(int i = 0;i<points.size();i++){
                        if(debut==points.get(i).getGeo_poi_id()){
                            arc.setGeo_arc_deb(points.get(i));

                        }
                    }
                    for(int i = 0;i<points.size();i++){
                        if(fin==points.get(i).getGeo_poi_id()){
                            arc.setGeo_arc_fin(points.get(i));
                        }
                    }

                    double temps = cursor.getDouble(cursor.getColumnIndex(C.FieldARC.TEMPS));
                    arc.setGeo_arc_temps(temps);

                    //double distance = cursor.getDouble(cursor.getColumnIndex(C.FieldARC.DISTANCE));
                    //arc.setGeo_arc_distance(distance);
                    double xLatLamb = convertDegToLamb(arc.getGeo_arc_deb().getGeo_poi_latitude(),arc.getGeo_arc_deb().getGeo_poi_longitude()).get(0);
                    double xLonLamb = convertDegToLamb(arc.getGeo_arc_deb().getGeo_poi_latitude(),arc.getGeo_arc_deb().getGeo_poi_longitude()).get(1);
                    double yLatLamb = convertDegToLamb(arc.getGeo_arc_fin().getGeo_poi_latitude(),arc.getGeo_arc_fin().getGeo_poi_longitude()).get(0);
                    double yLonLamb = convertDegToLamb(arc.getGeo_arc_fin().getGeo_poi_latitude(),arc.getGeo_arc_fin().getGeo_poi_longitude()).get(1);

                    arc.setGeo_arc_distance(distancePoint(xLatLamb,xLonLamb,yLatLamb,yLonLamb));


                    int sens = cursor.getInt(cursor.getColumnIndex(C.FieldARC.SENS));
                    arc.setGeo_arc_sens(sens);
                    arcs.add(arc);

                } while (cursor.moveToNext());
            }
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return arcs;
    }

}

