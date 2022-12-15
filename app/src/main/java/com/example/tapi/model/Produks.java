package com.example.tapi.model;

import com.google.gson.annotations.SerializedName;

public class Produks {
    @SerializedName("id")
    private Integer id;

    @SerializedName("namaproduk")
    private String namaproduk;

    @SerializedName("harga")
    private Integer harga;

    @SerializedName("jumlah")
    private Integer jumlah;

    @SerializedName("deskripsi")
    private String deskripsi;

    public Produks(Integer id, String namaproduk, Integer harga, Integer jumlah, String deskripsi) {
        this.id = id;
        this.namaproduk = namaproduk;
        this.harga = harga;
        this.jumlah = jumlah;
        this.deskripsi = deskripsi;
    }

    public String getNamaproduk() {
        return namaproduk;
    }

    public Integer getHagra() {
        return harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}
