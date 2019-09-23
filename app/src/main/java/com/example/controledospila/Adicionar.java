package com.example.controledospila;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Adicionar extends AppCompatActivity {

    EditText edtDesc;
    EditText edtValor;
    EditText edtData;
    EditText edtLocal;
    EditText edtCategoria;
    Button btnAdicionarGasto;
    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        edtDesc = findViewById(R.id.edtDesc);
        edtValor = findViewById(R.id.edtValor);
        edtData = findViewById(R.id.edtData);
        edtLocal = findViewById(R.id.edtLocal);
        edtCategoria = findViewById(R.id.edtCategoria);
        btnAdicionarGasto = findViewById(R.id.btnAdicionrGasto);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnAdicionarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper((getBaseContext()));
                Gastos gasto = new Gastos(edtDesc.getText().toString(), Double.valueOf(edtValor.getText().toString()), edtData.getText().toString(), edtLocal.getText().toString(), edtCategoria.getText().toString());
                GastosDao gastosDao = new GastosDao(getBaseContext());

                String msg = gastosDao.save(gasto);
                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Adicionar.this, MainActivity.class);
                startActivity(it);
            }
        });




    }
}
