package com.example.tapi;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapi.model.Produks;

import java.util.List;

public class ProduksAdapter extends RecyclerView.Adapter<ProduksAdapter.ViewHolder> {

    private List<Produks> produksList;

    public ProduksAdapter(List<Produks> produksList){
        this.produksList =produksList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.card_title.setText(produksList.get(position).getNamaproduk());
        holder.card_description.setText(produksList.get(position).getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return produksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView card_title, card_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card_title = itemView.findViewById(R.id.card_title);
            card_description = itemView.findViewById(R.id.card_description);
        }
    }
}
