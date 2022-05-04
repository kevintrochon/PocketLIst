package nc.unc.ktrochon.pocketlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import nc.unc.ktrochon.pocketlist.adapter.AddProduitAdapter;
import nc.unc.ktrochon.pocketlist.adapter.ListProduitAdpter;
import nc.unc.ktrochon.pocketlist.entity.Produit;
import nc.unc.ktrochon.pocketlist.service.AppartenirService;
import nc.unc.ktrochon.pocketlist.service.ProduitServices;

public class AddProduitToListActivity extends AppCompatActivity implements View.OnClickListener {

    private ProduitServices services = new ProduitServices();
    private AppartenirService appartenirService = new AppartenirService();
    private AddProduitAdapter adapter;
    private FloatingActionButton button;
    private List<Produit> list;
    private List<Produit> produitList;
    private int numeroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produit_to_list);
        button = findViewById(R.id.create_list_prod);
        button.setOnClickListener(this);
        button.show();
        numeroList = getIntent().getIntExtra("numeroDeLaList",-1);
        list = services.getAll(this);
        adapter = new AddProduitAdapter(list,this);
        RecyclerView recyclerView = findViewById(R.id.add_produit_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getTag() != null && !hasChecked(view)){
            // Add one product on the shopping list.
            Log.i("Add_product :","Add one product on the shopping list.");
            addOnlyOneProductOnList(view,numeroList);
        }
        else{
            // Add all products on the shopping list.
            Log.i("Add_product :","Add all products on the shopping list where the checkbox is checked.");
            for (Produit p:
                 list) {
                if(hasChecked(findViewById(R.id.check_add_produit))){
                    this.produitList.add(p);
                }
            }
            addAllProductsOnList(view,this.produitList,numeroList);
        }
    }

    public boolean hasChecked(View view){
        boolean check = false;
        CheckBox checkBox = (CheckBox)view.findViewById(R.id.check_add_produit);
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
}