package com.edu.senac.projetinho.ui;

import android.os.Bundle;

import com.edu.senac.projetinho.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Conversao extends AppCompatActivity {

    Spinner converterDe;
    EditText edtNumero;
    TextView edtKB,edtMB,edtGB,edtTB,error;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversao);

        edtNumero=findViewById(R.id.edtNumero);
        converterDe=findViewById(R.id.converterDe);
        edtKB=findViewById(R.id.edtKB);
        edtMB=findViewById(R.id.edtMB);
        edtGB=findViewById(R.id.edtGB);
        edtTB=findViewById(R.id.edtTB);
        error=findViewById(R.id.errorTxt);

    converterDe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            converterNum(null);
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    });
    }

    public void converterNum(View v){
        String cnvtDe=converterDe.getSelectedItem().toString();
        NumberFormat formatter = new DecimalFormat("0.00");

        if(edtNumero.getText().toString().trim().length() > 0) {
            Double edtNum = Double.parseDouble(edtNumero.getText().toString());
            error.setText("");
        if (cnvtDe.equals("KB")){
            edtKB.setText(formatter.format(edtNum));
            edtMB.setText(formatter.format(edtNum/1024));
            edtGB.setText(formatter.format(edtNum/1048576));
            edtTB.setText(formatter.format(edtNum/1073741824));
        }else if(cnvtDe.equals("MB")){
            edtKB.setText(formatter.format(edtNum*1024));
            edtMB.setText(formatter.format(edtNum));
            edtGB.setText(formatter.format(edtNum/1024));
            edtTB.setText(formatter.format(edtNum/1048576));
        }else if(cnvtDe.equals("GB")){
            edtKB.setText(formatter.format((edtNum*1048576)));
            edtMB.setText(formatter.format(edtNum*1024));
            edtGB.setText(formatter.format(edtNum));
            edtTB.setText(formatter.format(edtNum/1024));
        }else{
            edtKB.setText(formatter.format(edtNum*1073741824));
            edtMB.setText(formatter.format(edtNum*1048576));
            edtGB.setText(formatter.format(edtNum*1024));
            edtTB.setText(formatter.format(edtNum));
        }
        }else{
            edtKB.setText("0");
            edtMB.setText("0");
            edtGB.setText("0");
            edtTB.setText("0");
            error.setText("Número informado inválido ou nulo");
        }
    }
}
