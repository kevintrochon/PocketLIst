package nc.unc.ktrochon.pocketlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import nc.unc.ktrochon.pocketlist.entity.CategoryProduit;
import nc.unc.ktrochon.pocketlist.entity.Produit;
import nc.unc.ktrochon.pocketlist.service.CategoryServices;
import nc.unc.ktrochon.pocketlist.service.ProduitServices;

public class AddProduit extends AppCompatActivity{

    private EditText nomProduit;
    private Spinner category;
    private EditText description;
    private ImageButton save;
    int numeroListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produit);
        save = findViewById(R.id.save_produit);
        category = findViewById(R.id.spinnerCategory);
        numeroListe = getIntent().getIntExtra("numeroDeLaListe",-1);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProduct();
            }
        });
        CategoryServices services = new CategoryServices();
        List<CategoryProduit> categories = services.getAllCategory(this);

        // Creating adapter for spinner
        ArrayAdapter<CategoryProduit> dataAdapter = new ArrayAdapter<CategoryProduit>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        category.setAdapter(dataAdapter);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

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

        ImageButton toolBarButton = findViewById(R.id.toolbarButton);
        toolBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProduit.this,AddProduitToListActivity.class);
                intent.putExtra("numeroDeLaList",numeroListe);
                startActivity(intent);
            }
        });

    }

    public void saveProduct(){
        nomProduit = findViewById(R.id.nomProduit);
        description = findViewById(R.id.descriptionProduit);
        category = findViewById(R.id.spinnerCategory);
        Produit produit = new Produit();
        produit.setNomProduit(nomProduit.getText().toString());
        produit.setDescription(description.getText().toString());
        produit.setCategory(((CategoryProduit)category.getSelectedItem()).getCategoryId());
        ProduitServices produitServices = new ProduitServices();
        produitServices.addProduit(this,produit);
        Intent intent = new Intent(this,AddProduitToListActivity.class);
        intent.putExtra("numeroDeLaList",numeroListe);
        startActivity(intent);
    }

}