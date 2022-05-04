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
    private CategoryProduit category;
    Appartenir appartenir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produit);
        FloatingActionButton button = findViewById(R.id.create_list_prod);
        button.setOnClickListener(this);
        button.show();
        Gson gson = new Gson();
        ListProduit listProduit = gson.fromJson(getIntent().getStringExtra("listProduits"),ListProduit.class);
        this.listProduit = listServices.getListProduitByName(this,listProduit.getName());

        produits = produitServices.getAllProduit(this, this.listProduit.getId());

        for (Produit p:
             produits) {
            appartenir = services.getAppartenir(this, this.listProduit.getId(),p.getId());
            category = caterogyServices.getCategoryById(this,p.getCategory());
        }

        produits = produitServices.getAllProduit(this,listProduit.getId());

        adapter = new ProduitAdapter(produits, this,category.getCategoryName(),appartenir.getQuantite());

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
        intent.putExtra("quantiteProduit",appartenir.getQuantite());
        startActivity(intent);
    }

}