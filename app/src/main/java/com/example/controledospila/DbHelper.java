package com.example.controledospila;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "BaseGastos.db";
    public static final String TABELA = "Gastos";
    public static final String ID = "_id";
    public static final String DESCRICAO = "descricao";
    public static final String DATA = "data";
    public static final String VALOR = "valor";
    public static final String LOCAL = "local";
    public static final String CATEGORIA = "categoria";
    public static final int VERSAO = 1;

    public DbHelper(Context context){
        super(context,NOME_BANCO,null,VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //aplicação sendo rodada pela primeira vez
        String sqlVersao1="CREATE TABLE "+ TABELA + " ("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DESCRICAO + " TEXT NOT NULL, " + DATA + " TEXT NOT NULL, " + VALOR + " DOUBLE, " + LOCAL + " TEXT, " + CATEGORIA + " TEXT NOT NULL)";
        db.execSQL(sqlVersao1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //atualização da base
        db.execSQL("Drop table if exists "+ TABELA);
        onCreate(db);

    }
}
