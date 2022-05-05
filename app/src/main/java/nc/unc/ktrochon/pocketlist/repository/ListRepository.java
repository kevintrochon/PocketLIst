package nc.unc.ktrochon.pocketlist.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import nc.unc.ktrochon.pocketlist.DAO.ListDAO;
import nc.unc.ktrochon.pocketlist.entity.ListProduit;

public class ListRepository extends AppCompatActivity {

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

        try {
            connexion.getWritableDatabase().execSQL(query);
        }catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Erreur d'ajout de la liste!"
                    ,Toast.LENGTH_SHORT).show();
        }finally {
            if (connexion !=null) {
                connexion.close();
            }
        }
    }

    public void deleteListe(ListProduit listProduit){
        String query = "DELETE FROM liste WHERE nom = '"
                + listProduit.getName()
                +"'";

        try {
            connexion.getWritableDatabase().execSQL(query);
        }catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Erreur de suppression de la liste!"
                    ,Toast.LENGTH_SHORT).show();
        }finally {
            if (connexion !=null) {
                connexion.close();
            }
        }
    }

    public void updateTitleList(ListProduit listProduit,String nom) {
        String query = "UPDATE liste SET nom ='"
                + nom
                +"' WHERE nom = '"
                + listProduit.getName()
                +"'";

        try {
            connexion.getWritableDatabase().execSQL(query);
        }catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Erreur de mise à jour de la liste!"
                    ,Toast.LENGTH_SHORT).show();
        }finally {
            if (connexion !=null) {
                connexion.close();
            }
        }
    }



}
