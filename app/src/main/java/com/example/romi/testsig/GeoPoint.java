package com.example.romi.testsig;

import java.util.Objects;

public class GeoPoint {


        private int geo_poi_id;
        private double geo_poi_latitude;
        private double geo_poi_longitude;
        private String geo_poi_nom;
        private int geo_poi_partition;

    public GeoPoint(int geo_poi_id, double geo_poi_latitude, double geo_poi_longitude, String geo_poi_nom, int geo_poi_partition) {
        this.geo_poi_id = geo_poi_id;
        this.geo_poi_latitude = geo_poi_latitude;
        this.geo_poi_longitude = geo_poi_longitude;
        this.geo_poi_nom = geo_poi_nom;
        this.geo_poi_partition = geo_poi_partition;
    }
    public GeoPoint() {

    }

    public int getGeo_poi_id() {
        return geo_poi_id;
    }

    public void setGeo_poi_id(int geo_poi_id) {
        this.geo_poi_id = geo_poi_id;
    }

    public double getGeo_poi_latitude() {
        return geo_poi_latitude;
    }

    public void setGeo_poi_latitude(double geo_poi_latitude) {
        this.geo_poi_latitude = geo_poi_latitude;
    }

    public double getGeo_poi_longitude() {
        return geo_poi_longitude;
    }

    public void setGeo_poi_longitude(double geo_poi_longitude) {
        this.geo_poi_longitude = geo_poi_longitude;
    }

    public String getGeo_poi_nom() {
        return geo_poi_nom;
    }

    public void setGeo_poi_nom(String geo_poi_nom) {
        this.geo_poi_nom = geo_poi_nom;
    }

    public int getGeo_poi_partition() {
        return geo_poi_partition;
    }

    public void setGeo_poi_partition(int geo_poi_partition) {
        this.geo_poi_partition = geo_poi_partition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoPoint geoPoint = (GeoPoint) o;
        return geo_poi_id == geoPoint.geo_poi_id &&
                Double.compare(geoPoint.geo_poi_latitude, geo_poi_latitude) == 0 &&
                Double.compare(geoPoint.geo_poi_longitude, geo_poi_longitude) == 0 &&
                geo_poi_partition == geoPoint.geo_poi_partition &&
                Objects.equals(geo_poi_nom, geoPoint.geo_poi_nom);
    }

    @Override
    public int hashCode() {

        return Objects.hash(geo_poi_id, geo_poi_latitude, geo_poi_longitude, geo_poi_nom, geo_poi_partition);
    }

    @Override
    public String toString() {
        return geo_poi_nom;
    }
}

