package nc.unc.ktrochon.pocketlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import nc.unc.ktrochon.pocketlist.entity.CategoryProduit;
import nc.unc.ktrochon.pocketlist.entity.Produit;
import nc.unc.ktrochon.pocketlist.service.CategoryServices;
import nc.unc.ktrochon.pocketlist.service.ProduitServices;

public class DetailsProduitActivity extends AppCompatActivity {
    Produit produit;
    int produitIndex = -1;
    TextView nomProduitView;
    TextView descriptionView;
    TextView categoryView;
    EditText title;
    EditText description;
    EditText category;
    CategoryServices services = new CategoryServices();
    ProduitServices produitServices = new ProduitServices();
    CategoryProduit categoryProduit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_produit);
        Gson gson = new Gson();
        produit = gson.fromJson(getIntent().getStringExtra("produits"), Produit.class);
        produitIndex = getIntent().getIntExtra("produitIndex",-1);
        nomProduitView = findViewById(R.id.title2);
        descriptionView = findViewById(R.id.text2);
        categoryView = findViewById(R.id.edit_produit);
        categoryProduit = services.getCategoryProduitByName(this,categoryView.getText().toString());
        nomProduitView.setText(produit.getNomProduit());
        descriptionView.setText(produit.getDescription());
        categoryView.setText(categoryProduit.getCategoryName());

    }

    public void sauvegarderProduit(View view){
        title = findViewById(R.id.title2);
        description = findViewById(R.id.text2);
        category = findViewById(R.id.edit_produit);
        categoryProduit = services.getCategoryProduitByName(this,category.getText().toString());
        nomProduitView.setText(title.getText());
        produit.setNomProduit(title.getText().toString());
        produit.setDescription(categoryProduit.getCategoryName());
        produit.setCategory(categoryProduit.getCategoryId());
        Produit p = produitServices.getProduitByName(this,produit.getNomProduit());
        if (p.getNomProduit() == null){
            produitServices.setProduit(this,produit);
        }else{
            produitServices.miseAJourNomProduit(this,produit,title.getText().toString());
        }
        Intent intent = new Intent(this, ListProduitActivity.class);
        startActivity(intent);
    }

}