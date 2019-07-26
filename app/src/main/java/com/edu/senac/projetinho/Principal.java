package com.edu.senac.projetinho;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void irParaCalculo(View v){
        Intent i = new Intent(this, CalculoIMC.class);
        startActivity(i);
    }

    public void irParaConversao(View v){
        Intent i = new Intent(this,Conversao.class);
        startActivity(i);
    }

    public void irParaSobre(View v){
        Intent i = new Intent(this,Sobre.class);
        startActivity(i);
    }
}
