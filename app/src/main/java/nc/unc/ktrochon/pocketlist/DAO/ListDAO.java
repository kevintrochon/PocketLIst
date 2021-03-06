package nc.unc.ktrochon.pocketlist.DAO;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ListDAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "caddie.db";
    private static final int DATABASE_VERSION = 1;

    //Constructeur
    public ListDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table Catégorie
        String strSql = "CREATE TABLE categorie ("
                +" key_cat INTEGER PRIMARY KEY AUTOINCREMENT, "
                +" nom VARCHAR(50) UNIQUE NOT NULL "
                + ")";

        //Table produit
        String strSql2 = "CREATE TABLE produit ("
                +" key_prod INTEGER PRIMARY KEY AUTOINCREMENT, "
                +" nom VARCHAR(50) UNIQUE NOT NULL, "
                +" description VARCHAR(150), "
                +" key_cat INTEGER, "
                +" FOREIGN KEY (key_cat) REFERENCES produit(key_cat))";

        //Table Liste
        String strSql3 = "CREATE TABLE liste ("
                +" key_liste INTEGER PRIMARY KEY, "
                +" nom VARCHAR(50) UNIQUE NOT NULL "
                +")";

        //Table Appartenir
        String strSql4 = "CREATE TABLE appartenir ("
                +" key_app INTEGER PRIMARY KEY AUTOINCREMENT, "
                +" key_liste INTEGER, "
                +" key_prod INTEGER, "
                +" quantite INTEGER, "
                +" FOREIGN KEY (key_liste) REFERENCES liste(key_liste), "
                +" FOREIGN KEY (key_prod) REFERENCES produit(key_prod))";
                
        try {
            Log.i("DATABASE", "onCreate Invoked");
            db.execSQL(strSql);
            Log.i("DATABASE", "Create Table Categorie");
            db.execSQL(strSql2);
            Log.i("DATABASE", "Create Table produits");
            db.execSQL(strSql3);
            Log.i("DATABASE", "Create Table Liste");
            db.execSQL(strSql4);
            Log.i("DATABASE", "Create Table Appartenir");
            String queryLIste = "INSERT INTO liste (key_liste,nom) VALUES(1,'liste de test')";
            db.execSQL(queryLIste);
            queryLIste = "INSERT INTO produit (nom,description,key_cat) VALUES" +
                    "('Pomme','Bon pour la santé',7), " +
                    "('Steak','Viande rouge',7), " +
                    "('Javel','Pour désinfecter',9), " +
                    "('Coca Cola','Au moins du 1.5l',4), " +
                    "('Papier toilette','En conditionnement de 12',9)";
            db.execSQL(queryLIste);
            queryLIste = "INSERT INTO appartenir (key_liste,key_prod,quantite) VALUES" +
                    "(1,1,5), " +
                    "(1,2,8), " +
                    "(1,3,1), " +
                    "(1,4,3), " +
                    "(1,5,2)";
            db.execSQL(queryLIste);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // Insertion des catégories
        String strSQLCat = "insert into categorie (nom) values " +
                "('Alcool'), " +
                "('Animaux'), " +
                "('Bazar'), " +
                "('Boisson'), " +
                "('Divers') , " +
                "('Drogerie'), " +
                "('Frais'), " +
                "('Surgelé'), " +
                "('Hygiène'), " +
                "('Parfumerie'), " +
                "('Textile'), " +
                "('Traditionnel')";
        db.execSQL(strSQLCat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String strSql1 = "DROP TABLE IF EXISTS produit";
        String strSql2 = "DROP TABLE IF EXISTS categorie";
        String strSql3 = "DROP TABLE IF EXISTS liste";
        String strSql4 = "DROP TABLE IF EXISTS appartenir";

        db.execSQL(strSql1);
        db.execSQL(strSql2);
        db.execSQL(strSql3);
        db.execSQL(strSql4);
        this.onCreate(db);
        Log.i("DATABASE", "onUpgrade Invoked");
    }
}
