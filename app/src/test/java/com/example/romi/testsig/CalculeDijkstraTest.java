package com.example.romi.testsig;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class CalculeDijkstraTest {

    private List<Noeud> nodes;
    private List<Arc> edges;
    @Test
    public void execute() {
        nodes = new ArrayList<Noeud>();
        edges = new ArrayList<Arc>();
        for (int i = 0; i < 6; i++) {
            Noeud location = new Noeud(i,1,0.0,0.0,"Noeud_"+i);
            nodes.add(location);
        }

        addLane(1,3, 1, 2, 1.0,1.0);
        addLane(2,3, 2, 3, 1.0,2.0);
        addLane(3,3, 3, 4, 1.0,3.0);
        addLane(4,3, 4, 5, 1.0,5.0);
        addLane(5,3, 2, 4, 1.0,4.0);
        addLane(6,3, 1, 4, 1.0,9.0);





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
    }
    private void addLane(int laneId,int sens, int sourceLocNo, int destLocNo,
                         double temp,double distance) {
        Arc lane = new Arc(laneId,sens,nodes.get(sourceLocNo),nodes.get(destLocNo),temp,distance);
        edges.add(lane);
    }
}