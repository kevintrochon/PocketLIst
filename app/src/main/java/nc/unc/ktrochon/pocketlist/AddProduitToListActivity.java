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
import nc.unc.ktrochon.pocketlist.service.ProduitServices;

public class AddProduitToListActivity extends AppCompatActivity implements View.OnClickListener {

    private ProduitServices services = new ProduitServices();
    private AddProduitAdapter adapter;
    private FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produit_to_list);
        button = findViewById(R.id.create_list_prod);
        button.setOnClickListener(this);
        button.show();
        List<Produit> list = services.getAll(this);
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
        }
        else{
            // Add all products on the shopping list.
            Log.i("Add_product :","Add all products on the shopping list where the checkbox is checked.");
        }
    }

    public boolean hasChecked(View view){
        boolean check = false;
        CheckBox checkBox = (CheckBox)view.findViewById(R.id.check_add_prod);
        if (checkBox.isChecked()){
            check = true;
        }
        return check;
    }
}