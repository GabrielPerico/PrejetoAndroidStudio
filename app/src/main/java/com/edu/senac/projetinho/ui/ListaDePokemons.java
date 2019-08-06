package com.edu.senac.projetinho.ui;

import android.content.Intent;
import android.os.Bundle;

import com.edu.senac.projetinho.R;
import com.edu.senac.projetinho.helper.AdapterList;
import com.edu.senac.projetinho.helper.AdapterListPkm;
import com.edu.senac.projetinho.helper.DatabaseHelper;
import com.edu.senac.projetinho.model.Pokedex;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaDePokemons extends AppCompatActivity {

    ListView listaPokemon;
    List<Pokedex> pokemons;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_pokemons);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent i = new Intent(this, CadastroPokemon.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.removeExtra("pokemon");
                startActivity(i);
            }
        });

        pokemons = new ArrayList<>();
        AdapterListPkm adapter = new AdapterListPkm(pokemons, this);
        listaPokemon = findViewById(R.id.lista);
        listaPokemon.setAdapter(adapter);

        databaseHelper = new DatabaseHelper(this);

        final Intent intent = new Intent(this, CadastroPokemon.class);

        listaPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent.putExtra("pokemon",pokemons.get(i));
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();

        pokemons = null;
        pokemons = databaseHelper.buscarTodosPkm();
        AdapterListPkm adapterList = (AdapterListPkm) listaPokemon.getAdapter();
        adapterList.atualizarPokemon(pokemons);
    }
}
