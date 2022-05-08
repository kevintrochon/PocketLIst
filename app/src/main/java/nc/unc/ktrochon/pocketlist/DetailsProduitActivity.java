package nc.unc.ktrochon.pocketlist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import nc.unc.ktrochon.pocketlist.entity.Appartenir;
import nc.unc.ktrochon.pocketlist.entity.CategoryProduit;
import nc.unc.ktrochon.pocketlist.entity.ListProduit;
import nc.unc.ktrochon.pocketlist.entity.Produit;
import nc.unc.ktrochon.pocketlist.service.AppartenirService;
import nc.unc.ktrochon.pocketlist.service.CategoryServices;
import nc.unc.ktrochon.pocketlist.service.ListServices;
import nc.unc.ktrochon.pocketlist.service.ProduitServices;

public class DetailsProduitActivity extends AppCompatActivity {
    Produit produit;
    int produitIndex = -1;
    ListProduit listProduit;
    TextView nomProduitView;
    TextView descriptionView;
    TextView quantityView;
    EditText title;
    EditText description;
    Spinner category;
    EditText quantity;
    CategoryServices services = new CategoryServices();
    ProduitServices produitServices = new ProduitServices();
    private AppartenirService appartenirService = new AppartenirService();
    private ListServices listServices = new ListServices();
    CategoryProduit categoryProduit;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_produit);
        Gson gson = new Gson();
        produit = gson.fromJson(getIntent().getStringExtra("produits"), Produit.class);
        listProduit = gson.fromJson(getIntent().getStringExtra("liste_de_produit"),ListProduit.class);
        produitIndex = getIntent().getIntExtra("produitIndex",-1);
        nomProduitView = findViewById(R.id.title2);
        descriptionView = findViewById(R.id.text2);
        quantityView = findViewById(R.id.quantite);
        spinner = (Spinner) findViewById(R.id.edit_produit);
        nomProduitView.setText(produit.getNomProduit());
        descriptionView.setText(produit.getDescription());
        quantityView.setText(String.valueOf(getIntent().getIntExtra("quantiteProduit",-1)));

        // Spinner Drop down elements
        List<CategoryProduit> categories = services.getAllCategory(this);

        // Creating adapter for spinner
        ArrayAdapter<CategoryProduit> dataAdapter = new ArrayAdapter<CategoryProduit>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // On selecting a spinner item
                Adapter adapter = adapterView.getAdapter();
                CategoryProduit category = (CategoryProduit) adapter.getItem(position);

                // Showing selected spinner item
                Toast.makeText(adapterView.getContext(), "Selection: " + category.getCategoryName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ImageButton deleteButton = findViewById(R.id.button_delete_produit);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProductInList();
            }
        });
    }


    public void sauvegarderProduit(View view){

        title = findViewById(R.id.title2);
        description = findViewById(R.id.text2);
        category = findViewById(R.id.edit_produit);
        quantity = findViewById(R.id.quantite);
        Produit p = new Produit();
        p.setId(produitIndex);
        p.setNomProduit(title.getText().toString());
        p.setDescription(description.getText().toString());
        categoryProduit = (CategoryProduit) spinner.getSelectedItem();
        p.setCategory(categoryProduit.getCategoryId());
        p.setCategoryName(categoryProduit.getCategoryName());
        if (p.getNomProduit() == null){
            produitServices.addProduit(this,produit);
        }else{
            produitServices.miseAJourNomProduit(this,produit,p);
            appartenirService.updateQuantity(this,listProduit.getId(),p.getId()+1,Integer.parseInt(quantity.getText().toString()));
        }
        Intent intent = new Intent(this, ListProduitActivity.class);
        Gson gson = new Gson();
        String listProduitJSON = gson.toJson(listProduit);
        intent.putExtra("listProduits",listProduitJSON);
        startActivity(intent);
    }

    public void deleteProductInList(){
        quantity = findViewById(R.id.quantite);
        appartenirService.deleteProductInList(this,produit.getId(),listProduit.getId(),Integer.parseInt(this.quantity.getText().toString()));
        Intent intent = new Intent(this,ListProduitActivity.class);
        Gson gson = new Gson();
        String listProduitJSON = gson.toJson(listProduit);
        intent.putExtra("listProduits",listProduitJSON);
        startActivity(intent);
    }

}