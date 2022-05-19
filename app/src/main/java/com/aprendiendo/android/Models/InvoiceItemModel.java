package com.aprendiendo.android.Models;

public class InvoiceItemModel {

    int id;
    private String name;
    int cant;
    private int priceU;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getPriceU() {
        return priceU;
    }

    public void setPriceU(int priceU) {
        this.priceU = priceU;
    }

    public int getPriceT() {
        return priceU*cant;
    }
}
