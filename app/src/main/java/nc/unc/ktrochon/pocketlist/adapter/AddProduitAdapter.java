package nc.unc.ktrochon.pocketlist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nc.unc.ktrochon.pocketlist.AddProduitToListActivity;
import nc.unc.ktrochon.pocketlist.R;
import nc.unc.ktrochon.pocketlist.entity.Produit;

public class AddProduitAdapter extends RecyclerView.Adapter<AddProduitAdapter.ViewHolder>{

    List<Produit> produits;
    View.OnClickListener itemClickListener;
    List<Integer> positions;
    String nomProduit;
    boolean isCheck;

    public AddProduitAdapter(List<Produit> list, AddProduitToListActivity itemClickListener) {
        this.produits = list;
        this.itemClickListener = itemClickListener;
        positions = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_add_produit,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produit produit = produits.get(position);
        holder.cardView.setOnClickListener(itemClickListener);
        holder.cardView.setTag(position);
        holder.titleView.setText(produit.getNomProduit());
        holder.checkBox.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    holder.checkBox.setSelected(isChecked);
                    positions.add(position);
                    nomProduit = produit.getNomProduit();
                    this.isCheck = isChecked;
                }
        );
    }

    @Override
    public int getItemCount() {
        return produits.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
        CardView cardView = itemView.findViewById(R.id.card_view_add);
        TextView titleView = cardView.findViewById(R.id.produit_name);
        CheckBox checkBox = cardView.findViewById(R.id.check_add_produit);
    }

    public List<Integer> getPositions(){
        return positions;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public boolean isCheck() {
        return isCheck;
    }
}
