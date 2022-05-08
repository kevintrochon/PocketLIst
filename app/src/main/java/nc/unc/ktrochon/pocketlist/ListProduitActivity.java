package nc.unc.ktrochon.pocketlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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
    private List<CategoryProduit> category;
    List<Appartenir> appartenir;


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
        appartenir = services.getAllAppartenir(this, this.listProduit.getId());
        category = caterogyServices.getAllCategory(this);
        adapter = new ProduitAdapter(produits, this,category,appartenir);

        RecyclerView recyclerView = findViewById(R.id.notes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        ImageButton toolBarButton = findViewById(R.id.toolbarButton);
        toolBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListProduitActivity.this,MainActivity.class);
                intent.putExtra("numeroDeLaList",listProduit.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getTag() != null)
            showNoteDetail((int)view.getTag());
        else{
            Intent intent = new Intent(this, AddProduitToListActivity.class);
            intent.putExtra("numeroDeLaList",listProduit.getId());
            startActivity(intent);
        }
    }

    public void showNoteDetail(int produitIndex){
        Produit produit = produits.get(produitIndex);
        Appartenir a = appartenir.get(produitIndex);
        List<ListProduit> listProduits = listServices.getAllList(this);
        ListProduit lp = listProduits.get(a.getKey_list()-1);
        Intent intent = new Intent(this, DetailsProduitActivity.class);
        Gson json = new Gson();
        String myJSON = json.toJson(produit);
        String listProduitJSON = json.toJson(lp);
        intent.putExtra("produits",myJSON);
        intent.putExtra("produitIndex",produitIndex);
        intent.putExtra("quantiteProduit",a.getQuantite());
        intent.putExtra("liste_de_produit",listProduitJSON);
        startActivity(intent);
    }

}