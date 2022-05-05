package com.aprendiendo.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.aprendiendo.android.databinding.ActivityConfirmPurchaseBinding;
import com.aprendiendo.android.databinding.ActivityMainBinding;
import com.bumptech.glide.Glide;

public class ConfirmPurchase extends AppCompatActivity {
        private ActivityConfirmPurchaseBinding binding;


    Integer amount = 1;
    Integer total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmPurchaseBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Bundle extras = getIntent().getExtras();
         String name = extras.getString("name");
         String image = extras.getString("image");
         Integer price = extras.getInt("price");
         Integer[] precio = {price};



         /*
        DisplayMetrics windowsize = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(windowsize);

        int width = windowsize.widthPixels;
        int height = windowsize.heightPixels;

        getWindow().setLayout((int)(width*0.9),(int)(height*0.5));
            */
        Glide.with(getApplicationContext()).load(image).into(binding.ivFullImage);

        binding.tvTittle.setText(name);
        binding.btConfirm.setText("$"+precio[0]);
        binding.tvAmount.setText(amount.toString());

        binding.btPlus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if(amount==1)
                {
                    amount = amount+1;
                    total = precio[0] * amount;

                    binding.tvAmount.setText(amount.toString());
                    binding.btConfirm.setText("$"+total);
                    binding.btLess.setEnabled(true);
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_less_red, null);
                    binding.btLess.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);

                }else
                {
                    amount = amount+1;
                    total = precio[0] * amount;
                    binding.tvAmount.setText(amount.toString());
                    binding.btConfirm.setText("$"+total);
                }

            }
        });

        binding.btLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                    if(amount==2)
                    {
                        amount = amount-1;
                        total = precio[0] * amount;
                        binding.btLess.setEnabled(false);
                        binding.btConfirm.setText("$"+total);
                        binding.tvAmount.setText(amount.toString());
                        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_less_black, null);
                        binding.btLess.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                    }
                    else
                    {
                        amount = amount -1;
                        total = precio[0] * amount;
                        binding.btConfirm.setText("$"+total);
                        binding.tvAmount.setText(amount.toString());

                    }

            }
        });

        binding.btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Agregado al Carrito",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Inicio.class);
                startActivity(intent);
            }
        });


    }




}