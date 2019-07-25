package com.edu.senac.projetinho;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Conversao extends AppCompatActivity {

    Spinner converterDe,converterPara;
    EditText edtNumero;
    TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversao);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNumero=findViewById(R.id.edtNumero);
        converterDe=findViewById(R.id.converterDe);
        converterPara=findViewById(R.id.converterPara);
        textResultado=findViewById(R.id.textResultado);
    }

    public void converterNum(View v){
        String cnvtDe=converterDe.getSelectedItem().toString(),cnvtPara=converterPara.getSelectedItem().toString();
        Integer edtNum=Integer.parseInt(edtNumero.getText().toString());
        if (cnvtDe == "KB"){
            if (cnvtPara == "KB"){
                textResultado.setText(Integer.toString((edtNum)));
            }else if(cnvtPara == "MB"){
                textResultado.setText(Integer.toString((edtNum/1024)));
            }else if(cnvtPara == "GB"){
                textResultado.setText(Integer.toString((edtNum/1048576)));
            }else{
                textResultado.setText(Integer.toString((edtNum/1073741824)));
            }
        }else if(cnvtDe == "MB"){
            if (cnvtPara == "KB"){
                textResultado.setText(Integer.toString((edtNum*1024)));
            }else if(cnvtPara == "MB"){
                textResultado.setText(Integer.toString((edtNum)));
            }else if(cnvtPara == "GB"){
                textResultado.setText(Integer.toString((edtNum/1024)));
            }else{
                textResultado.setText(Integer.toString((edtNum/1048576)));
            }
        }else if(cnvtDe == "GB"){
            if (cnvtPara == "KB"){
                textResultado.setText(Integer.toString(((edtNum*1048576))));
            }else if(cnvtPara == "MB"){
                textResultado.setText(Integer.toString((edtNum*1024)));
            }else if(cnvtPara == "GB"){
                textResultado.setText(Integer.toString((edtNum)));
            }else{
                textResultado.setText(Integer.toString((edtNum/1024)));
            }
        }else{
            if (cnvtPara == "KB"){
                textResultado.setText(Integer.toString((edtNum*1073741824)));
            }else if(cnvtPara == "MB"){
                textResultado.setText(Integer.toString((edtNum*1048576)));
            }else if(cnvtPara == "GB"){
                textResultado.setText(Integer.toString((edtNum*1024)));
            }else{
                textResultado.setText(Integer.toString((edtNum)));
            }
        }
    }

}
