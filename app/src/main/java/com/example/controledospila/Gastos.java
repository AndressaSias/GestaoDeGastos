package com.example.controledospila;

public class Gastos {

    private int _id;
    private String descricao;
    private double valor;
    private String data;
    private String local;
    private String categoria;

    public Gastos(int id, String descricao, double valor, String data, String local, String categoria) {
        _id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.local = local;
        this.categoria = categoria;
    }

    public Gastos(String descricao, double valor, String data, String local, String categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.local = local;
        this.categoria = categoria;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


}
