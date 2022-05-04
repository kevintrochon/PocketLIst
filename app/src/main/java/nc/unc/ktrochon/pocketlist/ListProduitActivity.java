package nc.unc.ktrochon.pocketlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import nc.unc.ktrochon.pocketlist.adapter.ProduitAdapter;
import nc.unc.ktrochon.pocketlist.entity.Appartenir;
import nc.unc.ktrochon.pocketlist.entity.CategoryProduit;
import nc.unc.ktrochon.pocketlist.entity.ListProduit;
import nc.unc.ktrochon.pocketlist.entity.Produit;
import nc.unc.ktrochon.pocketlist.service.AppartenirService;
import nc.unc.ktrochon.pocketlist.service.CategoryServices;
import nc.unc.ktrochon.pocketlist.service.ListServices;
import nc.unc.ktrochon.pocketlist.service.ProduitServices;

public class ListProduitActivity extends AppCompatActivity implements View.OnClickListener {

    List<Produit> produits;
    ProduitAdapter adapter;
    private AppartenirService services = new AppartenirService();
    private ProduitServices produitServices = new ProduitServices();
    private ListServices listServices = new ListServices();
    private CategoryServices caterogyServices = new CategoryServices();
    private ListProduit listProduit;
    private CategoryServices category;
    private List<Appartenir> allAppartenir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produit);
        FloatingActionButton button = findViewById(R.id.create_list_prod);
        button.setOnClickListener(this);
        button.show();
        Gson gson = new Gson();
        ListProduit produit = gson.fromJson(getIntent().getStringExtra("listProduits"),ListProduit.class);
        listProduit = listServices.getListProduitByName(this,produit.getName());
       // category = caterogyServices.getCategoryProduitByName(this,produit.getId());
        produits = produitServices.getAllProduit(this,listProduit.getId());
        allAppartenir = services.getAllAppartenir(this,listProduit.getId());
        adapter = new ProduitAdapter(produits, this);

        RecyclerView recyclerView = findViewById(R.id.notes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getTag() != null)
            showNoteDetail((int)view.getTag());
        else{
            Intent intent = new Intent(this, AddProduitToListActivity.class);
            startActivity(intent);
        }
    }

    public void showNoteDetail(int produitIndex){
        Produit produit = produits.get(produitIndex);
        Intent intent = new Intent(this, DetailsProduitActivity.class);
        Gson json = new Gson();
        String myJSON = json.toJson(produit);
        intent.putExtra("produits",myJSON);
        intent.putExtra("produitIndex",produitIndex);
        intent.putExtra("quantiteProduit",allAppartenir.get(0).getQuantite());
        startActivity(intent);
    }

    /*public void ajouterProduit(View view){
        Produit p = new Produit();
        Intent intent = new Intent(this, DetailsProduitActivity.class);
        Gson json = new Gson();
        String myJSON = json.toJson(p);
        intent.putExtra("produits",myJSON);
        intent.putExtra("produitIndex",-1);
        intent.putExtra("listeProduitName",listProduit.getName());
        startActivity(intent);
    }*/
}