package com.example.romi.testsig;

/**
 * Created by lance on 03/02/2017.
 */

public final class C {

    public static final class Database {
        public static final String NAME = "lp_iem_sig.sqlite";
        public static final int VERSION = 5;

    }
    public static final class Table {
        public static final String ARC = "GEO_ARC";
        public static final String POINT = "GEO_POINT";


    }
    public static final class FieldARC {
        public static final String ID = "GEO_ARC_ID";
        public static final String DEB = "GEO_ARC_DEB";
        public static final String FIN = "GEO_ARC_FIN";
        public static final String TEMPS = "GEO_ARC_TEMPS";
        public static final String DISTANCE = "GEO_ARC_DISTANCE";
        public static final String SENS = "GEO_ARC_SENS";
    }

    public static final class FieldPOINT {
        public static final String ID = "GEO_POINT_ID";
        public static final String LATITUDE = "GEO_POINT_LATITUDE";
        public static final String LONGTITUDE = "GEO_POINT_LONGITUDE";
        public static final String NOM = "GEO_POINT_NOM";
        public static final String PARTITION = "GEO_POINT_PARTITION";

    }
}
