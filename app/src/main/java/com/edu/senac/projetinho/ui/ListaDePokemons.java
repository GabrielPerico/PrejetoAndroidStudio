package com.edu.senac.projetinho.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.edu.senac.projetinho.R;
import com.edu.senac.projetinho.helper.AdapterListPkm;
import com.edu.senac.projetinho.helper.DatabaseHelper;
import com.edu.senac.projetinho.model.Pokedex;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaDePokemons extends AppCompatActivity {

    private AlertDialog alerta;
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

        listaPokemon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                popup(pokemons.get(i));
                return false;
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }
    public void popup(Pokedex pokedex){
        final Intent intent = new Intent(this, CadastroPokemon.class);
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.popup, null);

        view.findViewById(R.id.Editar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                intent.putExtra("pokemon",pokedex);
                startActivity(intent);
                alerta.dismiss();
            }
        });

        view.findViewById(R.id.Deletar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.removerPkm(pokedex);
                Toast.makeText(ListaDePokemons.this, "Pokemon removido", Toast.LENGTH_SHORT).show();
                refresh();
                alerta.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Opções para: "+pokedex.getNome());
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }
    public void refresh(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        pokemons = null;
        pokemons = databaseHelper.buscarTodosPkm();
        AdapterListPkm adapterList = (AdapterListPkm) listaPokemon.getAdapter();
        adapterList.atualizarPokemon(pokemons);
    }
}
