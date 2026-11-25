package com.example.marketing.domain.model;


public class Produkt {
    private Long id;
    private String name;
    private double preis;

    public Produkt() {
    }

    public Produkt(Long id, String name, double preis) {
        this.id = id;
        this.name = name;
        this.preis = preis;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPreis() {
        return preis;
    }
    public void setPreis(double preis) {
        this.preis = preis;
    }

}


