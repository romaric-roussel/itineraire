package com.example.lance.filmv5;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lance on 03/02/2017.
 */

public class GestionBDFIlm extends SQLiteOpenHelper {


    Integer[] idPoint = {1,2,3,6,6,4,8,8,9,7};




    /**
     * Requette de creation de la table film
     */
    public static final String CREATION_TABLE_FILM =
            "CREATE TABLE " + C.Table.FILM + " ( "
                    + C.Field.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + C.Field.TITRE + " TEXT, "
                    + C.Field.ANNEE + " INTEGER, "
                    + C.Field.NOTE + " INTEGER, "
                    + C.Field.IMAGE + " TEXT, "
                    + C.Field.RESUMER + " TEXT, "
                    + C.Field.COMENTAIRE + " TEXT, "
                    + C.Field.ACTEUR1 + " TEXT, "
                    + C.Field.ACTEUR2 + " TEXT, "
                    + C.Field.ACTEUR3 + " TEXT, "
                    + C.Field.ACTEUR4 + " TEXT, "
                    + C.Field.ACTEUR5 + " TEXT, "
                    + C.Field.VU + " INTEGER );";

    /**
     * Requette de suppréssion de la table film
     */
    public static final String SUPPRIMER_TABLE_FILM =
        "DROP TABLE IF EXISTS " + C.Table.FILM + " ;";



    /**
     * Constructeur de la class
     * @param context   contexte d'appel
     * @param name      Nom de la base de donnée
     * @param factory   une fabrique de curseur ou le plus souvent null
     * @param version   Numéro de version du shéma de la base de données
     */
    public GestionBDFIlm(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATION_TABLE_FILM);
        ContentValues enregistrement = new ContentValues();

        //sta wars 1
        enregistrement.put(C.Field.TITRE, "Star Wars: Episode 1 - La menace fantôme");
        enregistrement.put(C.Field.ANNEE, 1999);
        enregistrement.put(C.Field.NOTE, 5);
        enregistrement.put(C.Field.IMAGE, "https://images-na.ssl-images-amazon.com/images/M/MV5BM2FmZGIwMzAtZTBkMS00M2JiLTk2MDctM2FlNTQ2OWYwZDZkXkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_SY1000_CR0,0,666,1000_AL_.jpg");
        enregistrement.put(C.Field.RESUMER, "Jar Jar Binks il marche dans le caca lol sa fait rire, Dark Maul un ange parti trop tot mdrrr, LA COURSE DE POD !!!");
        enregistrement.put(C.Field.COMENTAIRE, "Jar Jar Binks confirmed Snoke, reddit approuved");
        enregistrement.put(C.Field.ACTEUR1, "Jar Jar Binks");
        enregistrement.put(C.Field.ACTEUR2, "Le gosse chiant");
        enregistrement.put(C.Field.ACTEUR3, "Sebulba");
        enregistrement.put(C.Field.ACTEUR4, "C3-PO a poil");
        enregistrement.put(C.Field.ACTEUR5, "R2-D2 en mode badass");
        enregistrement.put(C.Field.VU, 0);
        db.insert(C.Table.FILM,C.Field.TITRE, enregistrement);




        //stars wars 2
        enregistrement.put(C.Field.TITRE, "Star Wars: Episode 2 - L'attaque des clones");
        enregistrement.put(C.Field.ANNEE, 2002);
        enregistrement.put(C.Field.NOTE, 5);
        enregistrement.put(C.Field.IMAGE, "https://images-na.ssl-images-amazon.com/images/M/MV5BNDRkYzA4OGYtOTBjYy00YzFiLThhYmYtMWUzMDBmMmZkM2M3XkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_SY1000_CR0,0,752,1000_AL_.jpg");
        enregistrement.put(C.Field.RESUMER, "Dooku il casse le cul de Anakin le fragile, apres sa fait des combats de Force genre un manga");
        enregistrement.put(C.Field.COMENTAIRE, "Rip Jango Feet");
        enregistrement.put(C.Field.ACTEUR1, "Jar Jar Binks");
        enregistrement.put(C.Field.ACTEUR2, "Devlin le long cou");
        enregistrement.put(C.Field.ACTEUR3, "Des pokemons");
        enregistrement.put(C.Field.ACTEUR4, "Natalie Bonne'man");
        enregistrement.put(C.Field.ACTEUR5, "Le gros dans le bar");
        enregistrement.put(C.Field.VU, 0);
        db.insert(C.Table.FILM,C.Field.TITRE, enregistrement);



        //star wars 3
        enregistrement.put(C.Field.TITRE, "Star Wars: Episode 3 - La revanche des Sith");
        enregistrement.put(C.Field.ANNEE, 2005);
        enregistrement.put(C.Field.NOTE, 5);
        enregistrement.put(C.Field.IMAGE, "https://images-na.ssl-images-amazon.com/images/M/MV5BNTc4MTc3NTQ5OF5BMl5BanBnXkFtZTcwOTg0NjI4NA@@._V1_SY1000_SX750_AL_.jpg");
        enregistrement.put(C.Field.RESUMER, "Dooku il se fait casser le cul par Anakin le dark boy, RIP in peace Mace Windu, I am the Senate!, AUTISTIC SCREECHING, Obi Wan il coupe les jambes du fragile lol");
        enregistrement.put(C.Field.COMENTAIRE, "Les combats c'est le cirque Pinder en scred loule'");
        enregistrement.put(C.Field.ACTEUR1, "Jar Jar Binks");
        enregistrement.put(C.Field.ACTEUR2, "Le senat");
        enregistrement.put(C.Field.ACTEUR3, "Le clone qui meurt en fond pendant la bataille de Kashyyyk");
        enregistrement.put(C.Field.ACTEUR4, "RIP Natalie Bonne'man");
        enregistrement.put(C.Field.ACTEUR5, "Les petit jedi RIP");
        enregistrement.put(C.Field.VU, 0);
        db.insert(C.Table.FILM,C.Field.TITRE, enregistrement);


        //star wars rogue one
        enregistrement.put(C.Field.TITRE, "Rogue One: A Star Wars Story");
        enregistrement.put(C.Field.ANNEE, 2016);
        enregistrement.put(C.Field.NOTE, 5);
        enregistrement.put(C.Field.IMAGE, "https://images-na.ssl-images-amazon.com/images/M/MV5BMjEwMzMxODIzOV5BMl5BanBnXkFtZTgwNzg3OTAzMDI@._V1_SY1000_SX675_AL_.jpg");
        enregistrement.put(C.Field.RESUMER, "DARK VADOR A LA FIN SA MERE LA PUTE");
        enregistrement.put(C.Field.COMENTAIRE, "DARK VADOR");
        enregistrement.put(C.Field.ACTEUR1, "Jar Jar Binks");
        enregistrement.put(C.Field.ACTEUR2, "DARK VADOR");
        enregistrement.put(C.Field.ACTEUR3, "DARK VADOR");
        enregistrement.put(C.Field.ACTEUR4, "DARK VADOR");
        enregistrement.put(C.Field.ACTEUR5, "DARK VADOR");
        enregistrement.put(C.Field.VU, 0);
        db.insert(C.Table.FILM,C.Field.TITRE, enregistrement);


        //star wars4
        enregistrement.put(C.Field.TITRE, "Star Wars: Episode 4 - Un nouvel espoir");
        enregistrement.put(C.Field.ANNEE, 1977);
        enregistrement.put(C.Field.NOTE, 5);
        enregistrement.put(C.Field.IMAGE, "https://images-na.ssl-images-amazon.com/images/M/MV5BZGEzZTExMDEtNjg4OC00NjQxLTk5NTUtNjRkNjA3MmYwZjg1XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SY1000_CR0,0,633,1000_AL_.jpg");
        enregistrement.put(C.Field.RESUMER, "Luke au debut c'est un gamin enfaite lmao, apres il sauve un gadji et apres il detruit un station spatiale de la taille de la lune en moins de 3 jours");
        enregistrement.put(C.Field.COMENTAIRE, "Rip Alderaan");
        enregistrement.put(C.Field.ACTEUR1, "Jar Jar Binks");
        enregistrement.put(C.Field.ACTEUR2, "Chictaba");
        enregistrement.put(C.Field.ACTEUR3, "Yan Solo");
        enregistrement.put(C.Field.ACTEUR4, "Le millenium condor");
        enregistrement.put(C.Field.ACTEUR5, "Greedo");
        enregistrement.put(C.Field.VU, 0);
        db.insert(C.Table.FILM,C.Field.TITRE, enregistrement);


        //star wars 5
        enregistrement.put(C.Field.TITRE, "Star Wars: Episode 5 - L'empire contre-attaque");
        enregistrement.put(C.Field.ANNEE, 1980);
        enregistrement.put(C.Field.NOTE, 5);
        enregistrement.put(C.Field.IMAGE, "https://images-na.ssl-images-amazon.com/images/M/MV5BYmViY2M2MTYtY2MzOS00YjQ1LWIzYmEtOTBiNjhlMGM0NjZjXkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_SY1000_CR0,0,644,1000_AL_.jpg");
        enregistrement.put(C.Field.RESUMER, "LA BTAILLE DE HOTH SA MERE, LES AT-AT !!!!!! RIP le genre de chameau du froid, Je suis ton père lol, la cité des nuages avec Lando en scred c'est pas un gentil");
        enregistrement.put(C.Field.COMENTAIRE, "A moi, a moi !!!!");
        enregistrement.put(C.Field.ACTEUR1, "Jar Jar Binks");
        enregistrement.put(C.Field.ACTEUR2, "Yoda dans les marais");
        enregistrement.put(C.Field.ACTEUR3, "Le daron de Luke");
        enregistrement.put(C.Field.ACTEUR4, "Le gros vers de terre");
        enregistrement.put(C.Field.ACTEUR5, "Greedo");
        enregistrement.put(C.Field.VU, 0);
        db.insert(C.Table.FILM,C.Field.TITRE, enregistrement);

        //star wars 6
        enregistrement.put(C.Field.TITRE, "Star Wars: Episode 6 - Le retour du Jedi");
        enregistrement.put(C.Field.ANNEE, 1893);
        enregistrement.put(C.Field.NOTE, 5);
        enregistrement.put(C.Field.IMAGE, "https://images-na.ssl-images-amazon.com/images/M/MV5BODllZjg2YjUtNWEzNy00ZGY2LTgyZmQtYTkxNDYyOWM3OTUyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SY1000_CR0,0,637,1000_AL_.jpg");
        enregistrement.put(C.Field.RESUMER, "Les ewoks y croivent que C3-Po c'est un dieu lul, sa construit une deuxieme Etoile de la Mort pour faire genre mais sa marche pas");
        enregistrement.put(C.Field.COMENTAIRE, "Les motos dans la foret");
        enregistrement.put(C.Field.ACTEUR1, "Jar Jar Binks");
        enregistrement.put(C.Field.ACTEUR2, "L'ewok qui jette les cailloux");
        enregistrement.put(C.Field.ACTEUR3, "L'autre ewok qui jette les cailloux");
        enregistrement.put(C.Field.ACTEUR4, "L'ewok qui ramasse les cailloux");
        enregistrement.put(C.Field.ACTEUR5, "L'autre ewok qui ramsse les cailloux");
        enregistrement.put(C.Field.VU,0);
        db.insert(C.Table.FILM,C.Field.TITRE, enregistrement);


        //star wars 7
        enregistrement.put(C.Field.TITRE, "Star Wars: Episode 7 - Le reveil de la Force");
        enregistrement.put(C.Field.ANNEE, 2015);
        enregistrement.put(C.Field.NOTE, 4);
        enregistrement.put(C.Field.IMAGE, "https://images-na.ssl-images-amazon.com/images/M/MV5BOTAzODEzNDAzMl5BMl5BanBnXkFtZTgwMDU1MTgzNzE@._V1_SY1000_CR0,0,677,1000_AL_.jpg");
        enregistrement.put(C.Field.RESUMER, "Sa construit un troisieme Etoile de la Mort mais sa marche toujours pas, le sabre de Kylo Ren il top cool quand même");
        enregistrement.put(C.Field.COMENTAIRE, "Rip Yan Solo");
        enregistrement.put(C.Field.ACTEUR1, "Jar Jar Binks");
        enregistrement.put(C.Field.ACTEUR2, "Blu-Rey");
        enregistrement.put(C.Field.ACTEUR3, "Chictaba en mode furax");
        enregistrement.put(C.Field.ACTEUR4, "Luke il parle trop");
        enregistrement.put(C.Field.ACTEUR5, "Dark Vador en mode barbecue");
        enregistrement.put(C.Field.VU, 0);
        db.insert(C.Table.FILM,C.Field.TITRE, enregistrement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(GestionBDFIlm.SUPPRIMER_TABLE_FILM);
        onCreate(db);

    }


}
