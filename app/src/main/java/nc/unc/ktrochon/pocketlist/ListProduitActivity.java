package nc.unc.ktrochon.pocketlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

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

        //A remplacer par une méthode de récupération en base de données.
        produits = new ArrayList<>();
        produits.add(new Produit("Carrotte","Ceci est un légume de couleur orange, la partie commestible de la plante est la racine.",1,new CategoryProduit("Legume")));
        produits.add(new Produit("Salade","Ceci est un légume de couleur verte, la partie commestible de la plante sont ses feuilles.",2,new CategoryProduit("Legume")));

        adapter = new ProduitAdapter(produits, this);

        RecyclerView recyclerView = findViewById(R.id.notes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getTag() != null)
            showNoteDetail((int)view.getTag());
    }

    public void showNoteDetail(int produitIndex){
        Produit produit = produits.get(produitIndex);
        Intent intent = new Intent(this, DetailsProduitActivity.class);
        Gson json = new Gson();
        String myJSON = json.toJson(produit);
        intent.putExtra("produits",myJSON);
        intent.putExtra("produitIndex",produitIndex);
        startActivity(intent);
    }

    public void ajouterProduit(View view){
        produits.add(new Produit("Pomme de terre","Ceci est un féculant, la partie commestible de la plante est la racine.", 4, new CategoryProduit("Feculant")));

        adapter = new ProduitAdapter(produits, this);

        RecyclerView recyclerView = findViewById(R.id.notes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }
}