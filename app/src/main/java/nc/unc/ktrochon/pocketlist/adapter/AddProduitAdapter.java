package nc.unc.ktrochon.pocketlist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nc.unc.ktrochon.pocketlist.AddProduitToListActivity;
import nc.unc.ktrochon.pocketlist.R;
import nc.unc.ktrochon.pocketlist.entity.Produit;

public class AddProduitAdapter extends RecyclerView.Adapter<AddProduitAdapter.ViewHolder>{

    List<Produit> produits;
    View.OnClickListener itemClickListener;

    public AddProduitAdapter(List<Produit> list, AddProduitToListActivity itemClickListener) {
        this.produits = list;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_add_produit,parent,false);
        return new AddProduitAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produit produit = produits.get(position);
        holder.cardView.setOnClickListener(itemClickListener);
        holder.cardView.setTag(position);
        holder.titleView.setText(produit.getNomProduit());
    }

    @Override
    public int getItemCount() {
        return produits.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
        CardView cardView = itemView.findViewById(R.id.list_prod_card_view);
        TextView titleView = cardView.findViewById(R.id.produit_name);
    }
}
