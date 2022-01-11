package com.example.bluedoll.models;

public class Maps {
    private String namaLokasi;
    private double lat;
    private double lng;


    public Maps(String namaLokasi, double lat, double lng) {
        this.namaLokasi = namaLokasi;
        this.lat = lat;
        this.lng = lng;
    }

    public String getNamaLokasi() {
        return namaLokasi;
    }

    public void setNamaLokasi(String namaLokasi) {
        this.namaLokasi = namaLokasi;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
