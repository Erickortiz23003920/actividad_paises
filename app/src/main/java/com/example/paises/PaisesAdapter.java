package com.example.paises;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import java.util.List;

public class PaisesAdapter extends RecyclerView.Adapter<PaisesAdapter.PaisesViewHolder> {
    private List<paises> countryList;

    public static class PaisesViewHolder extends RecyclerView.ViewHolder{
        public TextView countryName;
        public ImageView countryFlag;

        public PaisesViewHolder(View itemView){
            super(itemView);
            countryName= itemView.findViewById(R.id.country_name);
            countryFlag= itemView.findViewById(R.id.country_flag);

        }
    }
    public PaisesAdapter(List<paises> countryList){
        this.countryList=countryList;
    }

    @NonNull
    @Override

    public PaisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int ViewType){
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        return new PaisesViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull PaisesViewHolder holder, int position){
        paises country = countryList.get(position);
        holder.countryName.setText(country.getName());
        Glide.with(holder.countryFlag.getContext()).load(country.getflagUrl()).into(holder.countryFlag);
    }
    @Override
    public int getItemCount(){
        return countryList.size();
    }
}