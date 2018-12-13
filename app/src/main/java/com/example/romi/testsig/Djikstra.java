package com.example.romi.testsig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Djikstra {

    private final List<GeoPoint> points;
    private final List<GeoArc> arcs;
    private Set<GeoPoint> settledPoints;
    private Set<GeoPoint> unSettledPoints;
    private Map<GeoPoint, GeoPoint> predecessors;
    private Map<GeoPoint, Double> distance;

    public Djikstra(Graphe graphe) {
        this.points = new ArrayList<GeoPoint>(graphe.getPoints());
        this.arcs = new ArrayList<GeoArc>(graphe.getArcs());
    }

    public void execute(GeoPoint source) {
        settledPoints = new HashSet<GeoPoint>();
        unSettledPoints = new HashSet<GeoPoint>();
        distance = new HashMap<GeoPoint, Double>();
        predecessors = new HashMap<GeoPoint, GeoPoint>();
        distance.put(source, 0.0);
        unSettledPoints.add(source);
        while (unSettledPoints.size() > 0) {
            GeoPoint node = getMinimum(unSettledPoints);
            settledPoints.add(node);
            unSettledPoints.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(GeoPoint point) {
        List<GeoPoint> adjacentPoint = getNeighbors(point);
        for (GeoPoint target : adjacentPoint) {
            if (getShortestDistance(target) > getShortestDistance(point)
                    + getDistance(point, target)) {
                distance.put(target, getShortestDistance(point)
                        + getDistance(point, target));
                predecessors.put(target, point);
                unSettledPoints.add(target);
            }
        }

    }

    private double getDistance(GeoPoint point, GeoPoint target) {
        for (GeoArc arc : arcs) {
            if (arc.getGeo_arc_deb().equals(point)
                    && arc.getGeo_arc_fin().equals(target)) {
                return arc.getGeo_arc_distance();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<GeoPoint> getNeighbors(GeoPoint point) {
        List<GeoPoint> neighbors = new ArrayList<GeoPoint>();
        for (GeoArc arc : arcs) {
            if (arc.getGeo_arc_deb().equals(point)
                    && !isSettled(arc.getGeo_arc_fin())) {
                neighbors.add(arc.getGeo_arc_fin());
            }
        }
        return neighbors;
    }

    private GeoPoint getMinimum(Set<GeoPoint> points) {
        GeoPoint minimum = null;
        for (GeoPoint point : points) {
            if (minimum == null) {
                minimum = point;
            } else {
                if (getShortestDistance(point) < getShortestDistance(minimum)) {
                    minimum = point;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(GeoPoint point) {
        return settledPoints.contains(point);
    }

    private double getShortestDistance(GeoPoint destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }

    public LinkedList<GeoPoint> getPath(GeoPoint target) {
        LinkedList<GeoPoint> path = new LinkedList<GeoPoint>();
        GeoPoint step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
}
