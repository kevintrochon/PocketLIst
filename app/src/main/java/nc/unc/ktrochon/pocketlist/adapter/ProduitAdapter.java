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
import nc.unc.ktrochon.pocketlist.entity.Appartenir;
import nc.unc.ktrochon.pocketlist.entity.CategoryProduit;
import nc.unc.ktrochon.pocketlist.entity.Produit;

public class ProduitAdapter extends RecyclerView.Adapter<ProduitAdapter.ViewHolder> {

    List<Produit> produits;
    List<Appartenir> appartenirs;
    List<CategoryProduit> categoryProduits;
    View.OnClickListener itemClickListener;

    public ProduitAdapter(List<Produit> produits, ListProduitActivity itemClickListener, List<CategoryProduit> category, List<Appartenir> appartenir) {
        this.produits = produits;
        this.itemClickListener = itemClickListener;
        this.categoryProduits = category;
        this.appartenirs = appartenir;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produit,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProduitAdapter.ViewHolder  holder,int position) {
        Produit produit = produits.get(position);
        Appartenir appartenir = appartenirs.get(position);
        CategoryProduit categoryProduit = categoryProduits.get(produit.getCategory()-1);
        holder.cardView.setOnClickListener(itemClickListener);
        holder.cardView.setTag(position);
        holder.titleView.setText(produit.getNomProduit());
        holder.categoryView.setText(categoryProduit.getCategoryName());
        holder.quantityView.setText(""+appartenir.getQuantite());
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
        TextView quantityView = cardView.findViewById(R.id.quantite);
    }
}
