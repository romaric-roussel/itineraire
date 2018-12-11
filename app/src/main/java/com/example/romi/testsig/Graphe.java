package com.example.romi.testsig;

import java.util.List;

public class Graphe {

    private final List<GeoPoint> points;
    private final List<GeoArc> arcs;

    public Graphe(List<GeoPoint> points, List<GeoArc> arcs) {
        this.points = points;
        this.arcs = arcs;
    }

    public List<GeoPoint> getPoints() {
        return points;
    }

    public List<GeoArc> getArcs() {
        return arcs;
    }
}
