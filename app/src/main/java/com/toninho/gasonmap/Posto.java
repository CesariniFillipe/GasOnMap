package com.toninho.gasonmap;

public class Posto {
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String LAT = "latitude";
    public static final String LONG = "longitude";
    public static final String GAS = "gasolina";
    public static final String ALC = "alcool";
    public static final String DIE = "diesel";
    public static final String TABLE = "Posto";

    private String lat, lng, gas, alc, die, nome;
    private String id;


    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public void setAlc(String alc) {
        this.alc = alc;
    }

    public void setDie(String die) {
        this.die = die;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getGas() {
        return gas;
    }

    public String getAlc() {
        return alc;
    }

    public String getDie() {
        return die;
    }

    public String getNome() {
        return nome;
    }
}
