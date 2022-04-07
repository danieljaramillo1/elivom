package com.aprendiendo.android.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aprendiendo.android.Models.Product;
import com.aprendiendo.android.databinding.CardItemBinding;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Handler;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>
{

    private Context where;
    CardItemBinding cardItemBinding;
    private ArrayList<Product> productArray;
    public ProductAdapter(Context where, ArrayList<Product> productArray)
    {
        this.where = where;
        this.productArray = productArray;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        cardItemBinding = CardItemBinding.inflate(LayoutInflater.from(where));
        return new ProductViewHolder(cardItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
            Product product = productArray.get(position);
            holder.itemBinding.tvName.setText(product.getName());
            holder.itemBinding.tvPrice.setText(String.valueOf(product.getPrice()));
            holder.itemBinding.tvRating.setText(String.valueOf(product.getRating()));
            String url = product.getImg_url();
            Drawable d = LoadImageFromWebOperations(url);
            holder.itemBinding.ivPicture.setImageDrawable(d);


    }

    @Override
    public int getItemCount() {
        return productArray.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        CardItemBinding itemBinding;
        public ProductViewHolder(@NonNull CardItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}

