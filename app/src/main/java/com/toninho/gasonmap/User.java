package com.toninho.gasonmap;


public class User {
    private String name, email, created_at;
    //private int id;
    public static final String TABLE = "Usuario";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";

    public User(String id, String email, String name) {
        this.name = name;
        this.email = email;
        //this.id = Integer.getInteger(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/
}
