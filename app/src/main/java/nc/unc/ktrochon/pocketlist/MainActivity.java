package nc.unc.ktrochon.pocketlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButtonList(View view){
        Intent intent = new Intent(this, ListProduitActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        intent.putExtra("idList",1);
        startActivity(intent);
        Log.i("MainActivity","Vous avez choisi une liste");
    }
}