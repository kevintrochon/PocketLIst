package nc.unc.ktrochon.pocketlist.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import nc.unc.ktrochon.pocketlist.DAO.ListDAO;
import nc.unc.ktrochon.pocketlist.entity.Produit;

public class ProduitRepository {

    private ListDAO connexion;

    public ProduitRepository(Context context){
        connexion = new ListDAO(context);
    }

    public ListDAO getConnexion() {
        return connexion;
    }

    public void setConnexion(ListDAO connexion) {
        this.connexion = connexion;
    }

    @SuppressLint("Range")
    public Produit getProduitByName(String nomProduit){
        Produit p = new Produit();
        Cursor cursor = connexion.getReadableDatabase().rawQuery("SELECT * FROM produit WHERE nom = '"+ nomProduit +"'",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
           p.setNomProduit(cursor.getString(cursor.getColumnIndex("nom")));
           p.setDescription(cursor.getString(cursor.getColumnIndex("nom")));
            cursor.moveToNext();
        }
        return p;
    }

    @SuppressLint("Range")
    public List<Produit> getAllProduit(){
        List<Produit> list = new ArrayList<>();
        Cursor cursor = connexion.getReadableDatabase().rawQuery("SELECT * FROM produit ",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Produit p = new Produit();
            p.setNomProduit(cursor.getString(cursor.getColumnIndex("nom")));
            p.setDescription(cursor.getString(cursor.getColumnIndex("nom")));
            list.add(p);
            cursor.moveToNext();
        }
        return list;
    }
}
