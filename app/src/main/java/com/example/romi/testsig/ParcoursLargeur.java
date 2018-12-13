package com.example.romi.testsig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class ParcoursLargeur {


    private LinkedList<Integer> adj[]; //liste d'adjacence
    private List<GeoPoint> points;
    private List<GeoArc> arcs;


    public ParcoursLargeur(Graphe graphe) {
        this.points = new ArrayList<GeoPoint>(graphe.getPoints());
        this.arcs = new ArrayList<GeoArc>(graphe.getArcs());

        adj = new LinkedList[points.size()];
        for (int i = 0; i < points.size(); ++i){
            adj[i] = new LinkedList();
          }
        for (int i = 0; i < arcs.size(); ++i){
            adj[arcs.get(i).getGeo_arc_deb().getGeo_poi_id()].add(arcs.get(i).getGeo_arc_fin().getGeo_poi_id());
        }

    }

        public List<Integer> execute (GeoPoint s)
        {
            List<Integer> liste_parcouru = new ArrayList<>();
            // Mark all the vertices as not visited(By default
            // set as false)
            boolean visited[] = new boolean[points.size()];

            // Create a queue for BFS
            LinkedList<Integer> queue = new LinkedList<Integer>();

            // Mark the current node as visited and enqueue it
            visited[s.getGeo_poi_id()] = true;
            queue.add(s.getGeo_poi_id());

            while (queue.size() != 0) {
                // Dequeue a vertex from queue and print it

                int poll = queue.poll();

                liste_parcouru.add(poll);


                // Get all adjacent vertices of the dequeued vertex s
                // If a adjacent has not been visited, then mark it
                // visited and enqueue it
                Iterator<Integer> i = adj[poll].listIterator();
                while (i.hasNext()) {
                    int n = i.next();
                    if (!visited[n]) {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
            return liste_parcouru;
        }

    }

