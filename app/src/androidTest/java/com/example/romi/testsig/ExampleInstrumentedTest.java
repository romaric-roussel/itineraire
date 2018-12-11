package com.example.romi.testsig;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

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
    private List<Noeud> nodes;
    private List<Arc> edges;
    private Dao dao;


    @Test
    public void useAppContext() throws SQLException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
    /*    DbHelper dbHelper = new DbHelper(appContext,"data.sqlite");
        dbHelper.createDataBase();
        dbHelper.getReadableDatabase();
        dao = new Dao(appContext);
        nodes = dao.getAllNoeud();
        edges = new ArrayList<Arc>();*/


        addLane(1, 3, 1, 2, 1.0, 1.0);
        addLane(2, 3, 2, 3, 1.0, 2.0);
        addLane(3, 3, 3, 4, 1.0, 3.0);
        addLane(4, 3, 4, 5, 1.0, 5.0);
        addLane(5, 3, 2, 4, 1.0, 4.0);
        addLane(6, 3, 1, 4, 1.0, 9.0);


        // Lets check from location Loc_1 to Loc_10
        Graphe graph = new Graphe(nodes, edges);
        CalculeDijkstra dijkstra = new CalculeDijkstra(graph);
        dijkstra.execute(nodes.get(1));
        LinkedList<Noeud> path = dijkstra.getPath(nodes.get(5));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (Noeud vertex : path) {
            System.out.println(vertex);
        }


    assertEquals("com.example.romi.testsig",appContext.getPackageName());
}
    private void addLane(int laneId,int sens, int sourceLocNo, int destLocNo,
                         double temp,double distance) {
        Arc lane = new Arc(laneId,sens,nodes.get(sourceLocNo),nodes.get(destLocNo),temp,distance);
        edges.add(lane);
    }
}
