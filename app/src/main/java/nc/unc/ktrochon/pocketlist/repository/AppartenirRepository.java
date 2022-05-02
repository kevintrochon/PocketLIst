package nc.unc.ktrochon.pocketlist.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import nc.unc.ktrochon.pocketlist.DAO.ListDAO;
import nc.unc.ktrochon.pocketlist.entity.Appartenir;

public class AppartenirRepository {

    private ListDAO connexion;
    List<Appartenir> list;

    public AppartenirRepository(Context context){
        connexion = new ListDAO(context);
    }

    @SuppressLint("Range")
    public List<Appartenir> getAllAppartenance(){
        List<Appartenir> list = new ArrayList<>();
        Cursor cursor = connexion.getReadableDatabase().rawQuery("SELECT * FROM appartenir",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Appartenir appartenir = new Appartenir();
            appartenir.setKey_list(cursor.getInt(cursor.getColumnIndex("key_liste")));
            appartenir.setKey_prod(cursor.getInt(cursor.getColumnIndex("key_prod")));
            appartenir.setQuantite(cursor.getInt(cursor.getColumnIndex("quantite")));
            list.add(appartenir);
            cursor.moveToNext();
        }
        return list;
    }

    @SuppressLint("Range")
    public List<Appartenir> getAppartenanceByListe(int numeroListe){
        List<Appartenir> list = new ArrayList<>();
        Cursor cursor = connexion.getReadableDatabase().rawQuery("SELECT * FROM appartenir WHERE key_liste = " + numeroListe,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Appartenir appartenir = new Appartenir();
            appartenir.setKey_list(cursor.getInt(cursor.getColumnIndex("key_liste")));
            appartenir.setKey_prod(cursor.getInt(cursor.getColumnIndex("key_prod")));
            appartenir.setQuantite(cursor.getInt(cursor.getColumnIndex("quantite")));
            list.add(appartenir);
            cursor.moveToNext();
        }
        return list;
    }

    public void addAppartenir(int numeroListe, int numeroProduit, int quantity) {

        String query = "INSERT INTO appartenir (key_liste,key_prod,quantite) VALUES ( "
                +numeroListe
                +","
                +numeroProduit
                +","
                + quantity
                + ")";

        connexion.getWritableDatabase().execSQL(query);
    }

}
