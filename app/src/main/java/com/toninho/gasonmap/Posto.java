package com.toninho.gasonmap;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Willian Soares on 13/06/2016.
 */
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

    public Posto(String id, String lat, String lng, String nome, String gas, String alc, String die ) {
        this.lat = lat;
        this.lng = lng;
        this.gas = gas;
        this.alc = alc;
        this.die = die;
        this.nome = nome;
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
