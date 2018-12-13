package com.example.romi.testsig;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private List<GeoPoint> pointList;
    private List<GeoArc> arcList;
    private Dao dao;
    private ParcoursLargeur parcoursLargeur;


    @Test
    public void useAppContext() throws SQLException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        dao = new Dao(appContext);
        dao.open();
        pointList = dao.getAllPoint();
        arcList = dao.getAllArc();
        Graphe graph = new Graphe(pointList, arcList);
        parcoursLargeur = new ParcoursLargeur(graph);
        List<Integer> test = parcoursLargeur.execute(pointList.get(1));
        for (Integer inte:test) {
            Log.d("TEST", inte.toString());
        }
    }

}
