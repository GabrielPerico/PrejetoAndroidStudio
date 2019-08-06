package com.edu.senac.projetinho.ui;

import android.content.Intent;
import android.os.Bundle;

import com.edu.senac.projetinho.R;
import com.edu.senac.projetinho.helper.AdapterList;
import com.edu.senac.projetinho.helper.DatabaseHelper;
import com.edu.senac.projetinho.model.Produto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaDeProdutos extends AppCompatActivity {

    ListView listaProdutos;
    List<Produto> produtos;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_produtos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent i = new Intent(this, CadastroProduto.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.removeExtra("produto");
                startActivity(i);
            }
        });

        produtos = new ArrayList<>();
        AdapterList adapter = new AdapterList(produtos, this);
        listaProdutos = findViewById(R.id.lista);
        listaProdutos.setAdapter(adapter);

        databaseHelper = new DatabaseHelper(this);

        final Intent intent = new Intent(this, CadastroProduto.class);

        listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent.putExtra("produto",produtos.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        produtos = null;
        produtos = databaseHelper.buscarTodos();
        AdapterList adapterList = (AdapterList) listaProdutos.getAdapter();
        adapterList.atualizarProdutos(produtos);
    }

}
