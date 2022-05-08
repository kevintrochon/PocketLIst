package nc.unc.ktrochon.pocketlist.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import nc.unc.ktrochon.pocketlist.DAO.ListDAO;
import nc.unc.ktrochon.pocketlist.entity.Produit;

public class ProduitRepository extends AppCompatActivity {

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
           p.setDescription(cursor.getString(cursor.getColumnIndex("description")));
           p.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("key_prod"))));
            cursor.moveToNext();
        }
        return p;
    }

    @SuppressLint("Range")
    public List<Produit> getAllProduit(int numeroList){
        List<Produit> list = new ArrayList<>();
        Cursor cursor = connexion.getReadableDatabase().rawQuery("SELECT * FROM produit INNER JOIN appartenir ON appartenir.key_prod = produit.key_prod WHERE appartenir.key_liste = " + numeroList,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Produit p = new Produit();
            p.setNomProduit(cursor.getString(cursor.getColumnIndex("nom")));
            p.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            p.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("key_prod"))));
            p.setCategory(Integer.parseInt(cursor.getString(cursor.getColumnIndex("key_cat"))));
            list.add(p);
            cursor.moveToNext();
        }
        return list;
    }

    @SuppressLint("Range")
    public List<Produit> getAll(){
        List<Produit> list = new ArrayList<>();
        Cursor cursor = connexion.getReadableDatabase().rawQuery("SELECT * FROM produit ",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Produit p = new Produit();
            p.setNomProduit(cursor.getString(cursor.getColumnIndex("nom")));
            p.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            p.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("key_prod"))));
            p.setCategory(Integer.parseInt(cursor.getString(cursor.getColumnIndex("key_cat"))));
            list.add(p);
            cursor.moveToNext();
        }
        return list;
    }

    public void addProduit(Produit produit) {

        String query = "INSERT INTO PRODUIT (Nom,Description,key_cat) VALUES ('"
                + produit.getNomProduit().replace("'","''")
                +"','"
                + produit.getDescription().replace("'","''")
                + "',"
                +produit.getCategory()
                + ")";

        try {
            connexion.getWritableDatabase().execSQL(query);
        }catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Erreur d'ajout de produit!"
                    ,Toast.LENGTH_SHORT).show();
        }finally {
            if (connexion !=null) {
                connexion.close();
            }
        }
    }

    public void deleteProduit(String nom){
        String query = "DELETE FROM produit WHERE nom = '"
                + nom
                +"'";

        try {
            connexion.getWritableDatabase().execSQL(query);
        }catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Erreur de suppression de produit!"
                    ,Toast.LENGTH_SHORT).show();
        }finally {
            if (connexion !=null) {
                connexion.close();
            }
        }
    }

    /*
    nom VARCHAR(50) UNIQUE NOT NULL, "
                +" description VARCHAR(150), "
                +" key_cat INTEGER, "
     */
    public void updateNomProduit(Produit produit,Produit newProduit) {
        String query = "UPDATE produit SET nom = '"
                + newProduit.getNomProduit()
                +"', description = '"
                + newProduit.getDescription()
                +"', key_cat = "
                + newProduit.getCategory()
                +" WHERE nom = '"
                + produit.getNomProduit()
                +"'";

        try {
            connexion.getWritableDatabase().execSQL(query);
        }catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Erreur de mise à jour du produit!"
                    ,Toast.LENGTH_SHORT).show();
        }finally {
            if (connexion !=null) {
                connexion.close();
            }
        }
    }

    public void updateDescriptionProduit(Produit produit,String newDescription) {
        String query = "UPDATE produit SET description ='"
                + newDescription
                +"' WHERE nom = '"
                + produit.getNomProduit()
                +"'";

        try {
            connexion.getWritableDatabase().execSQL(query);
        }catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Erreur de mise à jour du produit!"
                    ,Toast.LENGTH_SHORT).show();
        }finally {
            if (connexion !=null) {
                connexion.close();
            }
        }
    }


}
