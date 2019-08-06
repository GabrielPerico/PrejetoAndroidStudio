package com.edu.senac.projetinho.ui;

import android.content.Intent;
import android.os.Bundle;

import com.edu.senac.projetinho.R;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_aplicativos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sobre:
                Intent i = new Intent(this, Sobre.class);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void irParaCalculo(View v) {
        Intent i = new Intent(this, CalculoIMC.class);
        startActivity(i);
    }

    public void irParaConversao(View v) {
        Intent i = new Intent(this, Conversao.class);
        startActivity(i);
    }

    public void irParaLista(View v) {
        Intent i = new Intent(this, ListaDeProdutos.class);
        startActivity(i);
    }

    public void irParaPokedex(View v){
        Intent i = new Intent(this,CadastroPokemon.class);
        startActivity(i);
    }
}
