package nc.unc.ktrochon.pocketlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import nc.unc.ktrochon.pocketlist.entity.Produit;

public class DetailsProduitActivity extends AppCompatActivity {
    Produit produit;
    int produitIndex = -1;
    TextView nomProduitView;
    TextView descriptionView;
    TextView categoryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_produit);
        /*produit = getIntent().getParcelableExtra("produits");
        produitIndex = getIntent().getIntExtra("produitIndex",1);

        nomProduitView = findViewById(R.id.title2);
        descriptionView = findViewById(R.id.text2);
        categoryView = findViewById(R.id.edit_produit);

        nomProduitView.setText(produit.getNomProduit());
        descriptionView.setText(produit.getDescription());
        categoryView.setText(produit.getCategory().getCategoryName());*/

    }

    public class companion {
        String EXTRA_NOTE = "note";
        String EXTRA_NOTE_INDEX = "noteIndex";
    }
}