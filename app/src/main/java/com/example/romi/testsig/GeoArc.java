package com.example.romi.testsig;

import java.util.Arrays;
import java.util.Objects;

public class GeoArc {

    private int geo_arc_id;
    private GeoPoint geo_arc_deb;
    private GeoPoint geo_arc_fin;
    private double geo_arc_temps;
    private double geo_arc_distance;
    private int geo_arc_sens;

    public GeoArc(int geo_arc_id, GeoPoint geo_arc_deb, GeoPoint geo_arc_fin, double geo_arc_temps, double geo_arc_distance, int geo_arc_sens) {
        this.geo_arc_id = geo_arc_id;
        this.geo_arc_deb = geo_arc_deb;
        this.geo_arc_fin = geo_arc_fin;
        this.geo_arc_temps = geo_arc_temps;
        this.geo_arc_distance = geo_arc_distance;
        this.geo_arc_sens = geo_arc_sens;
    }

    public GeoArc() {
    }

    public int getGeo_arc_id() {
        return geo_arc_id;
    }

    public void setGeo_arc_id(int geo_arc_id) {
        this.geo_arc_id = geo_arc_id;
    }

    public GeoPoint getGeo_arc_deb() {
        return geo_arc_deb;
    }

    public void setGeo_arc_deb(GeoPoint geo_arc_deb) {
        this.geo_arc_deb = geo_arc_deb;
    }

    public GeoPoint getGeo_arc_fin() {
        return geo_arc_fin;
    }

    public void setGeo_arc_fin(GeoPoint geo_arc_fin) {
        this.geo_arc_fin = geo_arc_fin;
    }

    public double getGeo_arc_temps() {
        return geo_arc_temps;
    }

    public void setGeo_arc_temps(double geo_arc_temps) {
        this.geo_arc_temps = geo_arc_temps;
    }

    public double getGeo_arc_distance() {
        return geo_arc_distance;
    }

    public void setGeo_arc_distance(double geo_arc_distance) {
        this.geo_arc_distance = geo_arc_distance;
    }

    public int getGeo_arc_sens() {
        return geo_arc_sens;
    }

    public void setGeo_arc_sens(int geo_arc_sens) {
        this.geo_arc_sens = geo_arc_sens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoArc geoArc = (GeoArc) o;
        return geo_arc_id == geoArc.geo_arc_id &&
                Double.compare(geoArc.geo_arc_temps, geo_arc_temps) == 0 &&
                Double.compare(geoArc.geo_arc_distance, geo_arc_distance) == 0 &&
                geo_arc_sens == geoArc.geo_arc_sens &&
                Objects.equals(geo_arc_deb, geoArc.geo_arc_deb) &&
                Objects.equals(geo_arc_fin, geoArc.geo_arc_fin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(geo_arc_id, geo_arc_deb, geo_arc_fin, geo_arc_temps, geo_arc_distance, geo_arc_sens);
    }

    @Override
    public String toString() {
        return geo_arc_deb + " " + geo_arc_fin;
    }
}
