package com.example.controledospila;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class listagem extends AppCompatActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        GastosDao crud = new GastosDao(getBaseContext());
        final Cursor cursor;
        cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {DbHelper.DESCRICAO, DbHelper.VALOR, DbHelper.DATA, DbHelper.LOCAL, DbHelper.CATEGORIA};
        int[] idViews = new int[] {R.id.txtNome, R.id.txtValorGasto,R.id.txtDataGasto,R.id.txtLocalGasto,R.id.txtCatGasto};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_gastos,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.list);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.ID));
                Intent intent = new Intent(listagem.this, Alterar.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

    }
}


