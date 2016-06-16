package com.toninho.gasonmap.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.toninho.gasonmap.Posto;
import com.toninho.gasonmap.User;

/**
 * Created by Willian Soares on 09/06/2016.
 */
public class DBController {
    private SQLiteDatabase db;
    private DBCreator creator;

    public DBController(Context c) {
        this.creator = new DBCreator(c);
    }

    public String registerUser(String name, String email, String password) {
        Cursor cursor;
        String[] fields = {User.EMAIL};
        String where = User.EMAIL + " LIKE \"" + email+"\"";
        long result;

        db = creator.getReadableDatabase();
        cursor = db.query(User.TABLE, fields, where, null, null, null, null, null);

        if (!(cursor.getCount() > 0)){//Não é coluna, é linhas
            ContentValues values;

            db = creator.getWritableDatabase();
            values = new ContentValues();
            values.put(User.NOME, name);
            values.put(User.EMAIL, email);
            values.put(User.SENHA, password);

            result = db.insert(User.TABLE, null, values);
            db.close();
            if (result == -1)
                return "Error nº" + result;
            else
                return "Success";
        }
        else
            return "E-mail already exists!";
    }


    public Cursor login(String email, String password){
        Cursor cursor;
        String [] fields = {User.ID, User.EMAIL, User.NOME};
        String where = User.EMAIL+" LIKE \""+email+"\" AND "+ User.SENHA+" LIKE \""+password+"\"";
        db = creator.getReadableDatabase();
        cursor = db.query(User.TABLE, fields, where, null, null, null, null, null);
        if (cursor.getCount() == 1){
                cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getAllPostos(){
        Cursor posto;
        String f[] = new String[]{Posto.ID, Posto.LAT, Posto.LONG, Posto.NOME, Posto.GAS, Posto.ALC, Posto.DIE };
        db = creator.getReadableDatabase();
        posto = db.query(Posto.TABLE, f, null, null, null, null, null, null);
        posto.moveToFirst();
        db.close();
        return posto;
    }

    //int id, double lat, double lng, String nome, double gas, double alc, double die
    public String setPosto(){
        Cursor cursor;
        String[] fields = {Posto.ID, Posto.LAT, Posto.LONG, Posto.NOME, Posto.GAS, Posto.ALC, Posto.DIE};
        long result;

        ContentValues values;

        db = creator.getWritableDatabase();
        values = new ContentValues();
        values.put(Posto.ID, 1);
        values.put(Posto.LAT, "-21.444383");
        values.put(Posto.LONG, "-45.950427");
        values.put(Posto.NOME, "Posto Tamandaré");
        values.put(Posto.GAS, "13,00");
        values.put(Posto.ALC, "13,13");
        values.put(Posto.DIE, "113,00");

        result = db.insert(Posto.TABLE, null, values);
        db.close();
        if (result == -1)
            return "Error nº" + result;
        else
            return "Success";
    }



    public Cursor loadData(String table, String[] fields) {
        Cursor cursor;

        db = creator.getReadableDatabase();
        cursor = db.query(table, fields, null, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }
}