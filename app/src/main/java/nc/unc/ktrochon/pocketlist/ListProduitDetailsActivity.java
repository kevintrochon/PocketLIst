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
     private String holdTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produit_details);
        Gson gson = new Gson();
        listProduit = gson.fromJson(getIntent().getStringExtra("liste"), ListProduit.class);
        index = getIntent().getIntExtra("listeIndex",-1);
        textView = findViewById(R.id.nameList);
        textView.setText(listProduit.getName());
        holdTitle = textView.getText().toString();
    }

    public void sauvegarderListe(View view){
        editText = findViewById(R.id.nameList);
        ListProduit maListe = services.getListProduitByName(this,holdTitle);
        if (maListe.getName() == null){
            ListProduit listProduit = new ListProduit(editText.getText().toString());
            services.setList(this,listProduit);
        }
        else {
            services.miseAJourListProduit(this,maListe,editText.getText().toString());
        }
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void supprimerListe(View view){
        ListProduit listProduit = services.getListProduitByName(this,textView.getText().toString());
        services.deleteList(this,listProduit);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}