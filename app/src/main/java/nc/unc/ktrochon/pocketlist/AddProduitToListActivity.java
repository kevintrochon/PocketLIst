package nc.unc.ktrochon.pocketlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import nc.unc.ktrochon.pocketlist.adapter.AddProduitAdapter;
import nc.unc.ktrochon.pocketlist.entity.ListProduit;
import nc.unc.ktrochon.pocketlist.entity.Produit;
import nc.unc.ktrochon.pocketlist.service.AppartenirService;
import nc.unc.ktrochon.pocketlist.service.ListServices;
import nc.unc.ktrochon.pocketlist.service.ProduitServices;

public class AddProduitToListActivity extends AppCompatActivity implements View.OnClickListener {

    private final ProduitServices services = new ProduitServices();
    private final AppartenirService appartenirService = new AppartenirService();
    private final ListServices listServices = new ListServices();
    private AddProduitAdapter adapter;
    private FloatingActionButton buttonAjoutList;
    private FloatingActionButton buttonAjoutProd;
    private List<Produit> list;
    private List<Produit> produitList;
    private int numeroList;
    private CheckBox checkBox;
    private List<Integer> positions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produit_to_list);
        checkBox = findViewById(R.id.check_add_produit);
        buttonAjoutList = findViewById(R.id.create_list_prod);
        buttonAjoutList.setOnClickListener(this);
        buttonAjoutList.show();
        buttonAjoutProd = findViewById(R.id.create_produit);
        numeroList = getIntent().getIntExtra("numeroDeLaList",-1);
        list = services.getAll(this);
        adapter = new AddProduitAdapter(list,this);

        buttonAjoutProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AddProduitToListActivity.this.adapter.isCheck()){
                    deleteProduct(adapter.getNomProduit());
                    Intent intent = new Intent(AddProduitToListActivity.this,AddProduitToListActivity.class);
                    intent.putExtra("numeroDeLaList",getIntent().getIntExtra("numeroDeLaList",-1));
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(AddProduitToListActivity.this,AddProduit.class);
                    intent.putExtra("numeroDeLaListe",getIntent().getIntExtra("numeroDeLaList",-1));
                    startActivity(intent);
                }
            }
        });
        buttonAjoutProd.show();

        RecyclerView recyclerView = findViewById(R.id.add_produit_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getTag() != null && !hasChecked(view)){
            addOnlyOneProductOnList(view,numeroList);
        }
        else if(view.getId() == R.id.create_list_prod){
            positions = adapter.getPositions();
            this.produitList = new ArrayList<>();
            for (Integer i:positions
                 ) {
                produitList.add(list.get(i));
            }
            addAllProductsOnList(view,this.produitList,numeroList);
        }
        Intent intent = new Intent(this,ListProduitActivity.class);
        List<ListProduit> listProduitList =  listServices.getAllList(this);
        ListProduit listProduit = listProduitList.get(numeroList-1);
        Gson gson = new Gson();
        String malist = gson.toJson(listProduit);
        intent.putExtra("listProduits",malist);
        startActivity(intent);
    }

    public boolean hasChecked(View view){
        boolean check = false;
        CheckBox checkBox = view.findViewById(R.id.check_add_produit);
        if (checkBox.isChecked()){
            check = true;
        }
        return check;
    }

    public void addOnlyOneProductOnList(View view, int numeroList){
        Produit p = list.get((int)view.getTag());
        appartenirService.add(this,numeroList,p.getId(),0);
    }

    public void addAllProductsOnList(View view,List<Produit> produitList,int numeroList){
        for (Produit p:produitList) {
            appartenirService.add(this,numeroList,p.getId(),0);
        }
    }

    public void deleteProduct(String nom){
        services.deleteProduit(this,nom);
    }
}