package com.example.controledospila;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Alterar extends AppCompatActivity {

    EditText edtDescAlterar;
    EditText edtValorAlterar;
    EditText edtDataAlterar;
    EditText edtLocalAlterar;
    EditText edtCategoriaAlterar;
    Button btnAlterarGasto;
    Button btnDeletarGasto;
    Cursor cursor;
    GastosDao crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");
        crud = new GastosDao(getBaseContext());

        edtDescAlterar = (EditText)findViewById(R.id.edtDescAlterar);
        edtValorAlterar = (EditText)findViewById(R.id.edtValorAlterar);
        edtDataAlterar = (EditText)findViewById(R.id.edtDataAlterar);
        edtLocalAlterar = (EditText)findViewById(R.id.edtLocalAlterar);
        edtCategoriaAlterar = (EditText)findViewById(R.id.edtCategoriaAlterar);
        btnAlterarGasto = (Button)findViewById(R.id.btnAlterarGasto);
        btnDeletarGasto = (Button)findViewById(R.id.btnDeletarGasto);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));

        edtDescAlterar.setText(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.DESCRICAO)));
        edtValorAlterar.setText(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.VALOR)));
        edtDataAlterar.setText(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.DATA)));
        edtLocalAlterar.setText(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.LOCAL)));
        edtCategoriaAlterar.setText(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.CATEGORIA)));

        btnAlterarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = crud.alteraRegistro(Integer.parseInt(codigo),
                        edtDescAlterar.getText().toString(),
                        Double.valueOf(edtValorAlterar.getText().toString()),
                        edtDataAlterar.getText().toString(),
                        edtLocalAlterar.getText().toString(),
                        edtCategoriaAlterar.getText().toString());
                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Alterar.this,listagem.class);
                startActivity(intent);
                finish();
            }
        });

        btnDeletarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = crud.deletaRegistro(Integer.parseInt(codigo));
                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Alterar.this,listagem.class);
                startActivity(intent);
                finish();
            }
        });
    }
}


