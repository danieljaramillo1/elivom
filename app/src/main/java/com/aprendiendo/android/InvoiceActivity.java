package com.aprendiendo.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aprendiendo.android.Adapters.InvoiceAdapter;
import com.aprendiendo.android.Models.InvoiceItemModel;
import com.aprendiendo.android.databinding.ActivityInvoiceBinding;

import java.util.ArrayList;

public class InvoiceActivity extends AppCompatActivity {

    ActivityInvoiceBinding invoiceBinding;
    InvoiceAdapter invoiceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        invoiceBinding = ActivityInvoiceBinding.inflate(getLayoutInflater());
        View view = invoiceBinding.getRoot();
        setContentView(view);
        ArrayList<InvoiceItemModel> invoiceArray = getIntent().getParcelableExtra("invoiceArray");
        Log.d("------taggg-----", "onCreate: "+invoiceArray.get(0).getName());
        invoiceAdapter = new InvoiceAdapter(getApplicationContext(), invoiceArray);
        invoiceBinding.rvInvoice.setHasFixedSize(true);
        invoiceBinding.rvInvoice.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        invoiceBinding.rvInvoice.setAdapter(invoiceAdapter);
        Toast.makeText(this, invoiceArray.get(0).getName(), Toast.LENGTH_SHORT).show();

    }
}