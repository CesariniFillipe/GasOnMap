package com.toninho.gasonmap.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.toninho.gasonmap.Posto;
import com.toninho.gasonmap.User;


public class DBCreator extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "a14034";
    public static final int VERSAO = 1;

    public DBCreator(Context context){
        super(context,DATABASE_NAME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ User.TABLE+"("
                + User.ID + " integer primary key autoincrement,"
                + User.EMAIL + " text,"
                + User.NOME + " text,"
                + User.SENHA + " text);";

        db.execSQL(sql);
        sql = "CREATE TABLE "+ Posto.TABLE+"("
                + Posto.ID + " integer primary key autoincrement,"
                + Posto.NOME + " text,"
                + Posto.LAT + " text,"
                + Posto.LONG + " text,"
                + Posto.GAS + " text,"
                + Posto.ALC + " text,"
                + Posto.DIE + " text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + User.TABLE);
        onCreate(db);
    }

}
