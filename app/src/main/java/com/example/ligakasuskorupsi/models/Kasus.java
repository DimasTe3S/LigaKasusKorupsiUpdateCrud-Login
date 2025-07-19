package com.example.ligakasuskorupsi.models;

public class Kasus {
    private int id;
    private String nama;
    private String deskripsi;
    private String fotoPath; // Path to the image file

    public Kasus() {
    }

    public Kasus(int id, String nama, String deskripsi, String fotoPath) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.fotoPath = fotoPath;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }
}