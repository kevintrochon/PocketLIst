package nc.unc.ktrochon.pocketlist.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import nc.unc.ktrochon.pocketlist.DAO.ListDAO;
import nc.unc.ktrochon.pocketlist.entity.CategoryProduit;

public class CategoryRepository {
    private ListDAO connexion;

    public CategoryRepository(Context context){
        connexion = new ListDAO(context);
    }

    @SuppressLint("Range")
    public CategoryProduit getCategoryByName(String category){
        CategoryProduit categoryProduit = new CategoryProduit();
        Cursor cursor = connexion.getReadableDatabase().rawQuery("SELECT * FROM categorie WHERE nom = '"+ category +"'",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            categoryProduit.setCategoryName(cursor.getString(cursor.getColumnIndex("nom")));
            categoryProduit.setCategoryId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("key_cat"))));
            cursor.moveToNext();
        }
        return categoryProduit;
    }

    @SuppressLint("Range")
    public List<CategoryProduit> getAllCategory(){
        List<CategoryProduit> categoryProduits = new ArrayList<>();
        Cursor cursor = connexion.getReadableDatabase().rawQuery("SELECT * FROM categorie",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CategoryProduit categoryProduit = new CategoryProduit();
            categoryProduit.setCategoryName(cursor.getString(cursor.getColumnIndex("nom")));
            categoryProduit.setCategoryId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("key_cat"))));
            categoryProduits.add(categoryProduit);
            cursor.moveToNext();
        }
        return categoryProduits;
    }

    @SuppressLint("Range")
    public CategoryProduit getCategoryById(int numeroCategory){
        CategoryProduit categoryProduit = new CategoryProduit();
        Cursor cursor = connexion.getReadableDatabase().rawQuery("SELECT * FROM categorie WHERE key_cat = " + numeroCategory,null);
        cursor.moveToFirst();
        if(!cursor.isAfterLast()) {
            categoryProduit.setCategoryName(cursor.getString(cursor.getColumnIndex("nom")));
            categoryProduit.setCategoryId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("key_cat"))));
            cursor.moveToNext();
        }
        return categoryProduit;
    }

}
