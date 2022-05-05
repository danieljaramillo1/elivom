package com.aprendiendo.android.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aprendiendo.android.Inicio;
import com.aprendiendo.android.Models.CategoryModel;
import com.aprendiendo.android.R;
import com.aprendiendo.android.databinding.CategoryItemBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<CategoryModel> categoryModels;
    Context where;
    String text;
    CategoryItemBinding categoryBinding;
    public static String selectedCategory = "null";
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

        //Toast.makeText(where,categoryModels.get(position).getCategoryName(),Toast.LENGTH_SHORT).show();
        holder.tv.setText(categoryModels.get(position).getCategoryName());
        holder.containerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                 selectedCategory = holder.tv.toString();
                 Inicio inicio = new Inicio();
                 inicio.GetProducts();
            }
        });
    }



    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv;
        LinearLayout containerItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivCategory);
            tv = itemView.findViewById(R.id.tvCategoryNames);
            containerItem = itemView.findViewById(R.id.llCategory);

        }
    }
}
