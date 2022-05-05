package com.aprendiendo.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.aprendiendo.android.Adapters.CategoryAdapter;
import com.aprendiendo.android.Adapters.ProductAdapter;
import com.aprendiendo.android.Models.CategoryModel;
import com.aprendiendo.android.Models.Product;
import com.aprendiendo.android.Models.User;
import com.aprendiendo.android.Services.CreateUserService;
import com.aprendiendo.android.Services.GetAllProducts;
import com.aprendiendo.android.databinding.ActivityInicioBinding;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.aprendiendo.android.Services.ipConfig;
import com.aprendiendo.android.Adapters.CategoryAdapter;
import static com.aprendiendo.android.Services.ipConfig.ip;

public class Inicio extends AppCompatActivity {


    private Retrofit retrofit;
    private ActivityInicioBinding inicioBinding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String llave = "sesion";
    ProductAdapter  productAdapter;
    String userAdress;
    ArrayList<CategoryModel> categoryModel;
    CategoryAdapter cadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicioBinding = ActivityInicioBinding.inflate(getLayoutInflater());
        View view = inicioBinding.getRoot();
        setContentView(view);

        GetProducts();
        initElements();
        Intent intent = getIntent();
        userAdress = this.preferences.getString("direccion","un direccion");



        inicioBinding.btCloseSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean(llave,false);
                editor.apply();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });



    }

    // inicializo un monton de cositas
    private void initElements()
    {
       // Log.d("....TAG....", "initElements function");

        preferences = getSharedPreferences("sesiones",Context.MODE_PRIVATE);
        editor = preferences.edit();
        inicioBinding.tvUserName.setText(userAdress);
        Integer[] categoryLogo= {R.drawable.burger,R.drawable.hotdog,R.drawable.pizzas,R.drawable.drinks,R.drawable.postre,R.drawable.pollo,R.drawable.desayuno,R.drawable.oriental,R.drawable.veggie,R.drawable.almuerzo,R.drawable.tacos};
        String[] categoryNames ={"Burger","hotdog","pizzas","Bebidas","Postres","Pollo","Desayuno","Oriental","Veggie","Almuerzo","Tacos"};
        categoryModel = new ArrayList<>();
        for (int i= 0;i<categoryLogo.length;i++)
        {
            CategoryModel model = new CategoryModel(categoryLogo[i],categoryNames[i]);
            categoryModel.add(model);
            //Toast.makeText(getApplicationContext(),categoryModel.get(i).getCategoryName(),Toast.LENGTH_SHORT).show();

        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(Inicio.this,LinearLayoutManager.HORIZONTAL,false);
        inicioBinding.rvCategories.setLayoutManager(layoutManager);
        inicioBinding.rvCategories.setItemAnimator(new DefaultItemAnimator());

        cadapter = new CategoryAdapter(Inicio.this,categoryModel);

        inicioBinding.rvCategories.setAdapter(cadapter);

    }


    public void GetProducts()
    {
        String esto =  CategoryAdapter.selectedCategory;
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


                        if (CategoryAdapter.selectedCategory.equals("null"))
                        {
                            productAdapter = new ProductAdapter(getApplicationContext(),arrayProducts);
                            inicioBinding.rvProduct.setHasFixedSize(true);
                            inicioBinding.rvProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            inicioBinding.rvProduct.setAdapter(productAdapter);
                        }
                        else
                        {
                            Iterator<Product> iterator = arrayProducts.iterator();
                            while(iterator.hasNext())
                            {
                                Product product = iterator.next();
                                if (!product.getCategory().equals(CategoryAdapter.selectedCategory))
                                {
                                        iterator.remove();
                                }
                            }
                            productAdapter = new ProductAdapter(??????????,arrayProducts);
                            inicioBinding.rvProduct.setHasFixedSize(true);
                            inicioBinding.rvProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            inicioBinding.rvProduct.setAdapter(productAdapter);
                        }
                    inicioBinding.tvUserName.setText("Bienvenido Tu direccion es: "+userAdress);

                }

            }
            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"there is a problem in conection:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }








}