package nc.unc.ktrochon.pocketlist.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import nc.unc.ktrochon.pocketlist.DAO.ListDAO;
import nc.unc.ktrochon.pocketlist.entity.Appartenir;

public class AppartenirRepository extends AppCompatActivity {

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

        try {
            connexion.getWritableDatabase().execSQL(query);
        }catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Erreur d'ajout dans la liste!"
                    ,Toast.LENGTH_SHORT).show();
        }finally {
            if (connexion !=null) {
                connexion.close();
            }
        }
    }

    public void updateProductList(int numeroListe, int numeroProduit, int newNumeroProduit) {

        String query = "UPDATE appartenir SET key_prod = "
                +newNumeroProduit
                +"WHERE key_prod = "
                +numeroProduit
                +" AND key_liste ="
                + numeroListe;

        try {
            connexion.getWritableDatabase().execSQL(query);
        }catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Erreur de mise à jour dans la liste!"
                    ,Toast.LENGTH_SHORT).show();
        }finally {
            if (connexion !=null) {
                connexion.close();
            }
        }
    }

    public void updateQuantity(int numeroListe, int numeroProduit, int newQuantity) {

        String query = "UPDATE appartenir SET quantite = "
                + newQuantity
                +" WHERE key_prod = "
                +numeroProduit
                +" AND key_liste ="
                + numeroListe;

        try {
            connexion.getWritableDatabase().execSQL(query);
        }catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Erreur de mise à jour dans la liste!"
                    ,Toast.LENGTH_SHORT).show();
        }finally {
            if (connexion !=null) {
                connexion.close();
            }
        }
    }

    public void deletedList(int numeroListe){
        String query = "DELETE FROM appartenir WHERE key_liste ="
                + numeroListe;

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

    public void deletedProductInList(int numeroProduit, int numeroListe){
        String query = "DELETE FROM appartenir WHERE key_liste ="
                + numeroListe
                +" AND key_prod = "
                +numeroProduit ;

        try {
            connexion.getWritableDatabase().execSQL(query);
        }catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Erreur de suppression dans la liste!"
                    ,Toast.LENGTH_SHORT).show();
        }finally {
            if (connexion !=null) {
                connexion.close();
            }
        }
    }

    @SuppressLint("Range")
    public Appartenir getAppartenirByListe(int numeroListe, int numeroProduit) {
        Appartenir appartenir = new Appartenir();
        Cursor cursor = connexion.getReadableDatabase().rawQuery("SELECT * FROM appartenir WHERE key_liste = " + numeroListe +" AND key_prod = "+ numeroProduit,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            appartenir.setKey_list(cursor.getInt(cursor.getColumnIndex("key_liste")));
            appartenir.setKey_prod(cursor.getInt(cursor.getColumnIndex("key_prod")));
            appartenir.setQuantite(cursor.getInt(cursor.getColumnIndex("quantite")));
            cursor.moveToNext();
        }
        return appartenir;
    }
}
