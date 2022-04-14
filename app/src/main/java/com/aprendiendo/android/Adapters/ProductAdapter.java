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
import com.aprendiendo.android.R;
import com.aprendiendo.android.databinding.CardItemBinding;
import com.bumptech.glide.Glide;

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
            Glide.with(where).load(product.getImg_url()).into(holder.itemBinding.ivPicture);
            Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar1);
            ;

            // rating starts stuff here*******
            switch (product.getRating())

            {

                case 1:
                    Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar1);
                    Glide.with(where).load("https://toppng.com/uploads/preview/free-png-grey-star-png-images-transparent-grey-star-icon-11562980999ctbeqsdgmf.png").into(holder.itemBinding.ivStar2);
                    Glide.with(where).load("https://toppng.com/uploads/preview/free-png-grey-star-png-images-transparent-grey-star-icon-11562980999ctbeqsdgmf.png").into(holder.itemBinding.ivStar3);
                    Glide.with(where).load("https://toppng.com/uploads/preview/free-png-grey-star-png-images-transparent-grey-star-icon-11562980999ctbeqsdgmf.png").into(holder.itemBinding.ivStar4);
                    Glide.with(where).load("https://toppng.com/uploads/preview/free-png-grey-star-png-images-transparent-grey-star-icon-11562980999ctbeqsdgmf.png").into(holder.itemBinding.ivStar5);
                break;
                case 2:
                    Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar1);
                    Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar2);
                    Glide.with(where).load("https://toppng.com/uploads/preview/free-png-grey-star-png-images-transparent-grey-star-icon-11562980999ctbeqsdgmf.png").into(holder.itemBinding.ivStar3);
                    Glide.with(where).load("https://toppng.com/uploads/preview/free-png-grey-star-png-images-transparent-grey-star-icon-11562980999ctbeqsdgmf.png").into(holder.itemBinding.ivStar4);
                    Glide.with(where).load("https://toppng.com/uploads/preview/free-png-grey-star-png-images-transparent-grey-star-icon-11562980999ctbeqsdgmf.png").into(holder.itemBinding.ivStar5);
                    break;
                case 3:
                    Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar1);
                    Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar2);
                    Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar3);
                    Glide.with(where).load("https://toppng.com/uploads/preview/free-png-grey-star-png-images-transparent-grey-star-icon-11562980999ctbeqsdgmf.png").into(holder.itemBinding.ivStar4);
                    Glide.with(where).load("https://toppng.com/uploads/preview/free-png-grey-star-png-images-transparent-grey-star-icon-11562980999ctbeqsdgmf.png").into(holder.itemBinding.ivStar5);
                    break;
                case 4:
                    Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar1);
                    Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar2);
                    Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar3);
                    Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar4);
                    Glide.with(where).load("https://toppng.com/uploads/preview/free-png-grey-star-png-images-transparent-grey-star-icon-11562980999ctbeqsdgmf.png").into(holder.itemBinding.ivStar5);
                    break;
                case 5:
                   Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar1);
                    Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar2);
                    Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar3);
                    Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar4);
                    Glide.with(where).load("https://esquilo.io/png/thumb/YeYZYIJFIwAnfaT-Gold-Star-PNG-Photos.png").into(holder.itemBinding.ivStar5);
                    break;
            }


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



}

