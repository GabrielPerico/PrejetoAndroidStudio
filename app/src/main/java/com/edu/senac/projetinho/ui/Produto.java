package com.edu.senac.projetinho.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.edu.senac.projetinho.R;

import java.util.ArrayList;
import java.util.List;

public class Produto extends AppCompatActivity {

    Spinner status;
    ImageView imagem;
    EditText edtQtd, edtNome;
    boolean imageSlct = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        imagem = findViewById(R.id.imagem);
        edtQtd = findViewById(R.id.edtQtd);
        edtNome = findViewById(R.id.edtNome);
        status = findViewById(R.id.edtStat);

    }

    public void tirarFoto(View v) {
        if (checkAndRequestPermissions()) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, 100);
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagem.setImageBitmap(imageBitmap);
            imageSlct = true;
        }
    }

    public boolean checkAndRequestPermissions() {
        int camera = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
            return false;
        }
        return true;
    }

    public void salvar(View v) {
        String mensagem = validarCampos();
        if (mensagem.length() == 0) {
            Toast.makeText(Produto.this,"Salvo",Toast.LENGTH_SHORT).show();
            salvarFormulario();
        } else {
            mensagemErro(mensagem);
        }
    }

    public String validarCampos() {
        if (!imageSlct) {
            Log.d("salvar", "Imagem não informada");
            return "O campo Imagem não foi informado!";
        }
        if (edtNome.getText().toString().trim().length() == 0) {
            Log.d("salvar", "Nome não informado");
            return "O campo Nome não foi informado!";
        }
        if (edtQtd.getText().toString().trim().length() == 0 || Integer.parseInt(edtQtd.getText().toString()) <= 0) {
            Log.d("salvar", "Quantidade não informada");
            return "O campo Quantidade não foi informada ou possui valor inválido!";
        }
        Log.d("salvar", "Campos informados");
        return "";
    }

    public void salvarFormulario() {

    }

    public void mensagemErro(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção");
        builder.setMessage(mensagem);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(Produto.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(Produto.this, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
}
