package com.aprendiendo.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aprendiendo.android.Adapters.InvoiceAdapter;
import com.aprendiendo.android.Models.InvoiceItemModel;
import com.aprendiendo.android.databinding.ActivityInvoiceBinding;
import java.io.Serializable;
import java.util.ArrayList;

public class InvoiceActivity extends AppCompatActivity {

    ActivityInvoiceBinding invoiceBinding;
    InvoiceAdapter invoiceAdapter;
    ArrayList<? extends Parcelable> invoiceArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        invoiceBinding = ActivityInvoiceBinding.inflate(getLayoutInflater());
        View view = invoiceBinding.getRoot();
        setContentView(view);



        // for delete
    /*
        invoiceArray = new ArrayList<>();
        InvoiceItemModel itemModel = new InvoiceItemModel();
        itemModel.setId(0);
        itemModel.setName("Hamburguesa");
        itemModel.setCant(2);
        itemModel.setPriceU(2000);
        invoiceArray.add(itemModel);
        */


        invoiceArray = (ArrayList<? extends Parcelable>) getIntent().getParcelableArrayListExtra("ARRAYLIST");
        invoiceAdapter = new InvoiceAdapter(getApplicationContext(), (ArrayList<InvoiceItemModel>) invoiceArray);
        invoiceBinding.rvInvoice.setHasFixedSize(true);
        invoiceBinding.rvInvoice.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        invoiceBinding.rvInvoice.setAdapter(invoiceAdapter);
        //Log.d("------taggg-----", "onCreate: "+String.valueOf(invoiceArray.get(0).toString()));
        //Toast.makeText(this, invoiceArray.get(0).getName(), Toast.LENGTH_SHORT).show();



        /*
        invoiceArray = (ArrayList<InvoiceItemModel>) getIntent().getSerializableExtra("invoiceArray");
        invoiceAdapter = new InvoiceAdapter(getApplicationContext(), invoiceArray);
        invoiceBinding.rvInvoice.setHasFixedSize(true);
        invoiceBinding.rvInvoice.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        invoiceBinding.rvInvoice.setAdapter(invoiceAdapter);
           Log.d("------taggg-----", "onCreate: "+invoiceArray.get(0).getName());
        Toast.makeText(this, invoiceArray.get(0).getName(), Toast.LENGTH_SHORT).show();

        ArtistArrayAdapter adapter = new ArtistArrayAdapter(this, artists);
        recyclerView = (RecyclerView) findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(llm);
        list.setAdapter( adapter );
         */

    }
}