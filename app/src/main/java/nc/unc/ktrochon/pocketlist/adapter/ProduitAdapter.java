package nc.unc.ktrochon.pocketlist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nc.unc.ktrochon.pocketlist.ListProduitActivity;
import nc.unc.ktrochon.pocketlist.R;
import nc.unc.ktrochon.pocketlist.entity.Produit;

public class ProduitAdapter extends RecyclerView.Adapter<ProduitAdapter.ViewHolder> {

    List<Produit> produits;
    View.OnClickListener itemClickListener;

    public ProduitAdapter(List<Produit> produits, ListProduitActivity itemClickListener) {
        this.produits = produits;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produit,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProduitAdapter.ViewHolder  holder, int position) {
        Produit produit = produits.get(position);
        holder.cardView.setOnClickListener(itemClickListener);
        holder.cardView.setTag(position);
        holder.titleView.setText(produit.getNomProduit());
//        holder.categoryView.setText(produit.getCategory().getCategoryName());
        holder.excerptView.setText(produit.getDescription());
    }

    @Override
    public int getItemCount() {
        return produits.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
        CardView cardView = itemView.findViewById(nc.unc.ktrochon.pocketlist.R.id.card_view);
        TextView titleView = cardView.findViewById(R.id.title);
        TextView categoryView = cardView.findViewById(R.id.category);
        TextView excerptView = cardView.findViewById(R.id.excerpt);
    }
}
