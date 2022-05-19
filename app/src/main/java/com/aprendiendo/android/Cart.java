package com.aprendiendo.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.aprendiendo.android.Adapters.CartAdapter;
import com.aprendiendo.android.Models.CartItemModel;
import com.aprendiendo.android.Models.InvoiceItemModel;
import com.aprendiendo.android.Services.CreateUserService;
import com.aprendiendo.android.Services.GetAllCartItems;
import com.aprendiendo.android.Services.GetAllProducts;
import com.aprendiendo.android.databinding.ActivityCartBinding;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.aprendiendo.android.Services.ipConfig.ip;

public class Cart extends AppCompatActivity {
    private ActivityCartBinding binding;
    Retrofit retrofit;
    CartAdapter cartAdapter;
    ArrayList<CartItemModel> cartProducts;
    ArrayList<InvoiceItemModel> invoiceArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ShowCart();


        binding.btB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invoiceArray = new ArrayList<>();
                InvoiceItemModel itemModel = new InvoiceItemModel();
                itemModel.setId(0);
                itemModel.setName("Hamburguesa");
                itemModel.setCant(1);
                itemModel.setPriceU(2000);
                invoiceArray.add(itemModel);

                Intent intent = new Intent(getApplicationContext(), InvoiceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ARRAYLIST", (Serializable) invoiceArray);
                intent.putExtra("BUNDLE", bundle);
                startActivity(intent);
            }
        });
    }



public void goToInvoice(){
    invoiceArray = new ArrayList<>();
    InvoiceItemModel itemModel = new InvoiceItemModel();
    itemModel.setId(0);
    itemModel.setName("Hamburguesa");
    itemModel.setCant(1);
    itemModel.setPriceU(2000);
    invoiceArray.add(itemModel);


    /*
    Intent intent = new Intent(SendActivity.this, PickContactsActivity.class);
    Bundle bundle;
    bundle.putParcelableArrayList("data", cons); // Be sure con is not null here
    intent.putExtras(bundle);


ArrayList<Object> object = new ArrayList<Object>();
Intent intent = new Intent(Current.class, Transfer.class);
 Bundle args = new Bundle();
  args.putSerializable("ARRAYLIST",(Serializable)object);
   intent.putExtra("BUNDLE",args);
    startActivity(intent);
     */

    Intent intent = new Intent(getApplicationContext(), InvoiceActivity.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("ARRAYLIST", (Serializable) invoiceArray);
    intent.putExtra("BUNDLE", bundle);
    startActivity(intent);

    /*Intent intent = new Intent(getApplicationContext(),InvoiceActivity.class);
    Bundle bundle = new Bundle();
    bundle.putParcelableArrayList("invoiceArray", (ArrayList<? extends Parcelable>) invoiceArray);
    intent.putExtras(bundle);
    startActivity(intent);
     */
        /*
            ArrayList<String> milista = new ArrayList<String>();
            Intent intent = new Intent(this, SegundaActivity.class);
            intent.putExtra("miLista", milista);
            startActivity(intent);

 */
}




     public void ShowCart(){

         retrofit = new Retrofit.Builder().baseUrl(ip).addConverterFactory(GsonConverterFactory.create()).build();
         GetAllCartItems service = retrofit.create(GetAllCartItems.class);
         Call<ArrayList<CartItemModel>> compras = service.GetCart();
         compras.enqueue(new Callback<ArrayList<CartItemModel>>() {
             @Override
             public void onResponse(Call<ArrayList<CartItemModel>> call, Response<ArrayList<CartItemModel>> response)
             {

                 if(!response.isSuccessful())
                 {
                     Toast.makeText(getApplicationContext(),"error no se recibio una respuesta de la api",Toast.LENGTH_SHORT).show();
                 }else
                 {
                     cartProducts = response.body();
                     cartAdapter = new CartAdapter(getApplicationContext(),cartProducts);
                     binding.rvCartProducts.setHasFixedSize(true);
                     binding.rvCartProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                     binding.rvCartProducts.setAdapter(cartAdapter);

                 }

             }

             @Override
             public void onFailure(Call<ArrayList<CartItemModel>> call, Throwable t) {

             }
         });
     }

}