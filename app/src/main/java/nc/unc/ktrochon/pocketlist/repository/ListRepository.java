package nc.unc.ktrochon.pocketlist.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nc.unc.ktrochon.pocketlist.DAO.ListDAO;
import nc.unc.ktrochon.pocketlist.entity.ListProduit;

public class ListRepository {

    private ListDAO connexion;

    public ListRepository(Context context){
        connexion = new ListDAO(context);
    }

    @SuppressLint("Range")
    public ListProduit getListesProduitByName(String name){
        ListProduit listProduit = new ListProduit();
        Cursor cursor = connexion.getReadableDatabase().rawQuery("SELECT * FROM liste WHERE nom = '"+ name +"'",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listProduit.setName(cursor.getString(cursor.getColumnIndex("nom")));
            listProduit.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("key_liste"))));
            listProduit.setKey_prod(Integer.parseInt(cursor.getString(cursor.getColumnIndex("key_prod"))));
            cursor.moveToNext();
        }
        return listProduit;
    }

    @SuppressLint("Range")
    public List<ListProduit> getAllListesProduit(){
        List<ListProduit> list = new ArrayList<>();
        Cursor cursor = connexion.getReadableDatabase().rawQuery("SELECT * FROM liste ",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ListProduit listProduit = new ListProduit();
            listProduit.setName(cursor.getString(cursor.getColumnIndex("nom")));
            listProduit.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("key_liste"))));
            listProduit.setKey_prod(Integer.parseInt(cursor.getString(cursor.getColumnIndex("key_prod"))));
            list.add(listProduit);
            cursor.moveToNext();
        }
        return list;
    }

    public void addListe(ListProduit listProduit) {
        String query = "INSERT INTO liste (Nom,key_prod) VALUES ('"
                + listProduit.getName().replace("'","''")
                +"',"
                + listProduit.getId()+
                ")";

        connexion.getWritableDatabase().execSQL(query);
    }

    public void deleteListe(ListProduit listProduit){
        String query = "DELETE FROM liste WHERE nom = '"
                + listProduit.getName()
                +"'";
        connexion.getWritableDatabase().execSQL(query);
    }

    public void updateTitleList(ListProduit listProduit,String nom) {
        String query = "UPDATE liste SET nom ='"
                + nom
                +"' WHERE nom = '"
                + listProduit.getName()
                +"'";
        connexion.getWritableDatabase().execSQL(query);
    }



}
