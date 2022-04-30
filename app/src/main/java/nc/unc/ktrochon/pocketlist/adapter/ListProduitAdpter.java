package nc.unc.ktrochon.pocketlist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nc.unc.ktrochon.pocketlist.R;
import nc.unc.ktrochon.pocketlist.entity.ListProduit;

public class ListProduitAdpter extends RecyclerView.Adapter<ListProduitAdpter.ViewHolder>{
    List<ListProduit> lists;
    View.OnClickListener itemClickListener;

    public ListProduitAdpter(List<ListProduit> lists, View.OnClickListener itemClickListener) {
        this.lists = lists;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_produit,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListProduit listProduit = lists.get(position);
        holder.cardView.setOnClickListener(itemClickListener);
        holder.cardView.setTag(position);
        holder.titleView.setText(listProduit.getName());
        holder.checkBox.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    holder.checkBox.setSelected(isChecked);

                }

        );
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView){
            super(itemView);
        }
        CardView cardView = itemView.findViewById(R.id.list_card_view);
        TextView titleView = cardView.findViewById(R.id.listName);
        CheckBox checkBox = cardView.findViewById(R.id.check_delete);
    }
}
