package com.edu.senac.projetinho;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculoIMC extends AppCompatActivity {


    EditText edtAltura,edtPeso;
    TextView resultFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_imc);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtAltura=findViewById(R.id.edtAltura);
        edtPeso=findViewById(R.id.edtPeso);
        resultFinal=findViewById(R.id.resultFinal);
    }


    public void calcular(View v){
        Float alt= Float.parseFloat(edtAltura.getText().toString()), pes=Float.parseFloat(edtPeso.getText().toString());
        Float tot = pes/(alt * alt);
        String total = Float.toString(tot),result;

        if(tot < 18.5){
            result = "Abaixo do peso";
        }else if(tot >= 18.5 && tot < 25){
            result = "Peso normal";
        }else if(tot >= 25 && tot < 30){
            result = "Sobrepeso";
        }else if(tot >= 30 && tot < 35){
            result = "Obesidade grau 1";
        }else if(tot >= 35 && tot < 40){
            result = "Obesidade grau 2";
        }else{
            result = "Obesidade grau 3";
        }

        resultFinal.setText(total+" = "+result);


    }
    public void limpar(View v){
        resultFinal.setText("");
        edtPeso.getText().clear();
        edtAltura.getText().clear();
    }
}
