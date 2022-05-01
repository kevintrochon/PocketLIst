package nc.unc.ktrochon.pocketlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import nc.unc.ktrochon.pocketlist.entity.Produit;

public class DetailsProduitActivity extends AppCompatActivity {
    Produit produit;
    int produitIndex = -1;
    TextView nomProduitView;
    TextView descriptionView;
    TextView categoryView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_produit);
        Gson gson = new Gson();
        produit = gson.fromJson(getIntent().getStringExtra("produits"), Produit.class);
        produitIndex = getIntent().getIntExtra("produitIndex",1);

        nomProduitView = findViewById(R.id.title2);
        descriptionView = findViewById(R.id.text2);
        categoryView = findViewById(R.id.edit_produit);

        nomProduitView.setText(produit.getNomProduit());
        descriptionView.setText(produit.getDescription());
//        categoryView.setText(produit.getCategory().getCategoryName());

    }

    public void sauvegarderProduit(View view){
        editText = findViewById(R.id.title2);
        nomProduitView.setText(editText.getText());
        Intent intent = new Intent(this, ListProduitActivity.class);
        startActivity(intent);
        Log.i("Sauvegarde",editText.getText().toString());
    }
}