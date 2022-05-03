package com.aprendiendo.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.aprendiendo.android.databinding.ActivityConfirmPurchaseBinding;
import com.aprendiendo.android.databinding.ActivityMainBinding;
import com.bumptech.glide.Glide;

public class ConfirmPurchase extends AppCompatActivity {
        private ActivityConfirmPurchaseBinding binding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmPurchaseBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Bundle extras = getIntent().getExtras();
         String name = extras.getString("name");
         String image = extras.getString("image");
         String price = extras.getString("price");

        Toast.makeText(this,price,Toast.LENGTH_SHORT).show();

         /*
        DisplayMetrics windowsize = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(windowsize);

        int width = windowsize.widthPixels;
        int height = windowsize.heightPixels;

        getWindow().setLayout((int)(width*0.9),(int)(height*0.5));
            */
        Glide.with(getApplicationContext()).load(image).into(binding.ivFullImage);
        binding.tvTittle.setText(name);
        binding.btConfirm.setText(price);

    }
}