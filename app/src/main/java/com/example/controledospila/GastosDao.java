package com.example.controledospila;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class GastosDao {

    //metodos necessários para eu acessar e manipular o BD
    private SQLiteDatabase db;
    private DbHelper banco;

    public GastosDao(Context context){
        banco = new DbHelper(context);
    }

    //create
    public String save(Gastos gastos){

        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        try{
            valores=new ContentValues();
            valores.put(banco.DESCRICAO, gastos.getDescricao());
            valores.put(banco.VALOR, gastos.getValor());
            valores.put(banco.DATA, gastos.getData());
            valores.put(banco.LOCAL, gastos.getLocal());
            valores.put(banco.CATEGORIA, gastos.getCategoria());

            resultado = db.insert(banco.TABELA,null,valores);
            db.close();

            if(resultado !=-1){
                return "Gasto inserido ="+ gastos.getDescricao();
            }
        }catch (SQLException e){
            Log.e("ERRO", e.getMessage());
        }
        return "Erro ao inserir!";
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos = {DbHelper.ID, DbHelper.DESCRICAO, DbHelper.VALOR, DbHelper.DATA, DbHelper.LOCAL, DbHelper.CATEGORIA};
        db = banco.getReadableDatabase();
        cursor = db.query(DbHelper.TABELA, campos, null, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    //listar
    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos = {DbHelper.ID, DbHelper.DESCRICAO, DbHelper.VALOR, DbHelper.DATA, DbHelper.LOCAL, DbHelper.CATEGORIA};
        String where = DbHelper.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(DbHelper.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    //alterar
    public String alteraRegistro(int id, String descricao, Double valor, String data, String local, String categoria){
        ContentValues valores;
        String where;
        long resultado;

        db = banco.getWritableDatabase();

        where = DbHelper.ID + "=" + id;

        try {
            valores = new ContentValues();
            valores.put(banco.DESCRICAO, descricao);
            valores.put(banco.VALOR, valor);
            valores.put(banco.DATA, data);
            valores.put(banco.LOCAL, local);
            valores.put(banco.CATEGORIA, categoria);

            resultado = db.update(DbHelper.TABELA,valores,where,null);
            db.close();

            if(resultado !=-1){
                return "Gasto alterado";
            }
        }catch (SQLException e){
            Log.e("ERRO", e.getMessage());
        }
        return "Erro ao inserir!";

        }

    //deletar
    public String deletaRegistro(int id){
        String where = DbHelper.ID + "=" + id;
        long resultado;
        db = banco.getReadableDatabase();

        try{
            resultado = db.delete(DbHelper.TABELA,where,null);
            db.close();

            if(resultado !=-1){
                return "Gasto deletado";
            }
        }catch (SQLException e){
            Log.e("ERRO", e.getMessage());
        }
        return "Erro ao inserir!";
    }

}
