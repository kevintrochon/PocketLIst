package nc.unc.ktrochon.pocketlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import nc.unc.ktrochon.pocketlist.entity.ListProduit;

public class ListProduitDetailsActivity extends AppCompatActivity {

    ListProduit listProduit;
    int index;
    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produit_details);
        Gson gson = new Gson();
        listProduit = gson.fromJson(getIntent().getStringExtra("liste"), ListProduit.class);
        index = getIntent().getIntExtra("listeIndex",1);
        textView = findViewById(R.id.nameList);
        textView.setText(listProduit.getName());
    }

    public void sauvegarderListe(View view){
        editText = findViewById(R.id.nameList);
        Log.i("Sauvegarde",editText.getText().toString());
    }

    public void supprimerListe(View view){

    }
}