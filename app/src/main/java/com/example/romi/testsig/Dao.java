package com.example.romi.testsig;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
                    double distance = cursor.getDouble(cursor.getColumnIndex(C.FieldARC.DISTANCE));
                    arc.setGeo_arc_distance(distance);
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

