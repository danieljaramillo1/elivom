package com.aprendiendo.android.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aprendiendo.android.Models.CategoryModel;
import com.aprendiendo.android.R;
import com.aprendiendo.android.databinding.CategoryItemBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<CategoryModel> categoryModels;
    Context where;
    CategoryItemBinding categoryBinding;

    public CategoryAdapter(Context where,ArrayList<CategoryModel> categoryModels)
    {
            this.where = where;
            this.categoryModels = categoryModels;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(categoryModels.get(position).getCategoryLogo());
        holder.tv.setText(categoryModels.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivCategory);
            tv = itemView.findViewById(R.id.tvcategoryName);

        }
    }
}
