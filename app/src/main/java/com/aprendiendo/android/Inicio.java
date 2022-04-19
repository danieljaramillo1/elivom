package com.aprendiendo.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.aprendiendo.android.Adapters.ProductAdapter;
import com.aprendiendo.android.Models.Product;
import com.aprendiendo.android.Models.User;
import com.aprendiendo.android.Services.CreateUserService;
import com.aprendiendo.android.Services.GetAllProducts;
import com.aprendiendo.android.databinding.ActivityInicioBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.aprendiendo.android.Services.ipConfig;

import static com.aprendiendo.android.Services.ipConfig.ip;

public class Inicio extends AppCompatActivity {

    private Retrofit retrofit;
    private ActivityInicioBinding inicioBinding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String llave = "sesion";
    ProductAdapter  productAdapter;
    Intent get = getIntent();
    String name = get.getStringExtra("name");
    ArrayList<String> categories;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicioBinding = ActivityInicioBinding.inflate(getLayoutInflater());
        View view = inicioBinding.getRoot();
        setContentView(view);
        GetProducts();
        initElements();



        inicioBinding.btCloseSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean(llave,false);
                editor.apply();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });



    }
    // inicializo los shared
    private void initElements()
    {
        preferences = getSharedPreferences("sesiones",Context.MODE_PRIVATE);
        editor = preferences.edit();
        categories = new ArrayList<>();
        categories.add("Hamburger");
        categories.add("hotdogs");
        categories.add("drinks");

    }



    public void GetProducts()
    {
        retrofit = new Retrofit.Builder().baseUrl(ip).addConverterFactory(GsonConverterFactory.create()).build();
        GetAllProducts service = retrofit.create(GetAllProducts.class);
        Product newProduct = new Product();
        Call<ArrayList<Product>> myProducts = service.GetProducts();
        myProducts.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if (!response.isSuccessful())
                {


                   return;
                }else {
                    ArrayList<Product> arrayProducts = response.body();
                    productAdapter = new ProductAdapter(getApplicationContext(),arrayProducts);
                    inicioBinding.tvUserName.setText("Welcome, "+name+".");

                    inicioBinding.rvProduct.setHasFixedSize(true);
                    inicioBinding.rvProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    inicioBinding.rvProduct.setAdapter(productAdapter);
                }

            }
            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"there is a problem in conection:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Metodo boton productos



}