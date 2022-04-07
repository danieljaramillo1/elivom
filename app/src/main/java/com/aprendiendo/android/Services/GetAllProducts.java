package com.aprendiendo.android.Services;

import com.aprendiendo.android.Models.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAllProducts {

    @GET("products")
    Call<ArrayList<Product>> GetProducts();
}
