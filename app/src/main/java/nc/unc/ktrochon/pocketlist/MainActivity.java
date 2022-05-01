package nc.unc.ktrochon.pocketlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import nc.unc.ktrochon.pocketlist.adapter.ListProduitAdpter;
import nc.unc.ktrochon.pocketlist.entity.ListProduit;
import nc.unc.ktrochon.pocketlist.service.ListServices;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListProduitAdpter adapter;
    List<ListProduit> maListeProduit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TO DO : remplacer par la lecture de la base de données, table listeCourse.
        FloatingActionButton button = findViewById(R.id.create_list_fab);
        button.setOnClickListener(this);
        button.show();
        ListServices services = new ListServices();
        maListeProduit= services.getAllList(this);
        adapter = new ListProduitAdpter(maListeProduit,this);
        RecyclerView recyclerView = findViewById(R.id.liste_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    public void ajouterListe(View view){
        ListProduit listProduit = new ListProduit("Ma liste de vetements");
        maListeProduit.add(listProduit);
        adapter = new ListProduitAdpter(maListeProduit,this);
        RecyclerView recyclerView = findViewById(R.id.liste_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getTag() != null && !hasChecked(view)) {
            showProduitDetail((int) view.getTag());
        }
        else if (view.getId() == R.id.create_list_fab){
            ajouterListe(view);
        }
        else {
            showListDetail((int) view.getTag());
        }
    }

    public boolean hasChecked(View view){
        boolean check = false;
        CheckBox checkBox = (CheckBox)view.findViewById(R.id.check_delete);
        if (checkBox.isChecked()){
            check = true;
        }
        return check;
    }

    public void showProduitDetail(int maListeIndex){
        ListProduit listProduit = maListeProduit.get(maListeIndex);
        Intent intent = new Intent(this, ListProduitActivity.class);
        Gson json = new Gson();
        String myJSON = json.toJson(listProduit);
        intent.putExtra("listProduits",myJSON);
        intent.putExtra("listProduitIndex",maListeIndex);
        startActivity(intent);
    }

    public void showListDetail(int position){
        ListProduit listProduit = maListeProduit.get(position);
        Intent intent = new Intent(this, ListProduitDetailsActivity.class);
        Gson json = new Gson();
        String myJSON = json.toJson(listProduit);
        intent.putExtra("liste",myJSON);
        intent.putExtra("listeIndex",position);
        startActivity(intent);
    }
}