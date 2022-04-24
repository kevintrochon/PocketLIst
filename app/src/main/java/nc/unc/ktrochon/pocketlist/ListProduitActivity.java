package nc.unc.ktrochon.pocketlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import nc.unc.ktrochon.pocketlist.adapter.ProduitAdapter;
import nc.unc.ktrochon.pocketlist.entity.CategoryProduit;
import nc.unc.ktrochon.pocketlist.entity.Produit;

public class ListProduitActivity extends AppCompatActivity implements View.OnClickListener {

    List<Produit> produits;
    ProduitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produit);

        produits = new ArrayList<>();
        produits.add(new Produit("Carrotte","Ceci est un légume de couleur orange, la partie commestible de la plante est la racine.",new CategoryProduit("Legume")));
        produits.add(new Produit("Salade","Ceci est un légume de couleur verte, la partie commestible de la plante sont ses feuilles.",new CategoryProduit("Legume")));

        adapter = new ProduitAdapter(produits, this);

        RecyclerView recyclerView = findViewById(R.id.notes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        // TO DO : Changer cette façon de faire.
        int id = 2131231212;
        if (view.getId() == id){
            showNoteDetail(1);
        }
        Log.i("ListProduitActivity",""+view.getId());
        if (view.getTag() != null){
            Log.i("ListProduitActivity","Vous avez sélectionné un produit");
        }
    }

    public void showNoteDetail(int produitIndex){
        Produit produit = produits.get(produitIndex);
        Intent intent = new Intent(this, DetailsProduitActivity.class);
        intent.putExtra("produits",produit.getNomProduit());
        intent.putExtra("produitIndex",produitIndex);
        startActivity(intent);
    }
}