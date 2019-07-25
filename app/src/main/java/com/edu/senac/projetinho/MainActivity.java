package com.edu.senac.projetinho;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {


    EditText edtSenha,edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Ciclo","Passando pelo método onCreate ...");
        setContentView(R.layout.activity_main);

        edtSenha=findViewById(R.id.edtSenha);
        edtEmail=findViewById(R.id.edtEmail);
    }

    public void onStart() {
        super.onStart();
        Log.i("Ciclo", "Passando pelo método onStart ...");
    }

    public void onRestart() {
        super.onRestart();
        Log.i("Ciclo", "Passando pelo método onRestart ...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ciclo", "Passando pelo método onStop ...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ciclo", "Passando pelo método onResume ...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ciclo", "Passando pelo método onPause ...");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ciclo", "Passando pelo método onDestroy ...");
    }

    public void irParaPrincipal(View v){
        String email=edtEmail.getText().toString(),senha=edtSenha.getText().toString();
        if(email.equals("aluno@senac.br") && senha.equals("1234")) {
            Intent i = new Intent(this, Principal.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Usuario ou senha incorretos", Toast.LENGTH_SHORT).show();
        }
    }
}
