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
import nc.unc.ktrochon.pocketlist.service.ListServices;

public class ListProduitDetailsActivity extends AppCompatActivity {

    ListProduit listProduit;
    int index;
    TextView textView;
    EditText editText;
    private ListServices services = new ListServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produit_details);
        Gson gson = new Gson();
        listProduit = gson.fromJson(getIntent().getStringExtra("liste"), ListProduit.class);
        index = getIntent().getIntExtra("listeIndex",-1);
        textView = findViewById(R.id.nameList);
        textView.setText(listProduit.getName());
    }

    public void sauvegarderListe(View view){
        editText = findViewById(R.id.nameList);
        ListProduit maListe = services.getListProduitByName(this,editText.getText().toString());
        if (maListe.getName() == null){
            ListProduit listProduit = new ListProduit(textView.getText().toString());
            services.setList(this,listProduit);
        }
        else {
            services.miseAJourListProduit(this,maListe,editText.getText().toString());
        }

    }

    public void supprimerListe(View view){
        ListProduit listProduit = services.getListProduitByName(this,textView.getText().toString());
        services.deleteList(this,listProduit);
    }
}