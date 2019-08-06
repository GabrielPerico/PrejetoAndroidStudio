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
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.edu.senac.projetinho.R;
import com.edu.senac.projetinho.helper.AdapterList;
import com.edu.senac.projetinho.helper.DatabaseHelper;
import com.edu.senac.projetinho.model.Produto;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CadastroProduto extends AppCompatActivity {

    Spinner status;
    ImageView imagem;
    EditText edtQtd, edtNome;
    Button btnExcluir;
    Produto pro;
    boolean imageSlct = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        imagem = findViewById(R.id.imagem);
        edtQtd = findViewById(R.id.edtQtd);
        edtNome = findViewById(R.id.edtNome);
        status = findViewById(R.id.edtStat);
        btnExcluir = findViewById(R.id.btnExcluir);

        Intent i = getIntent();
        pro = (Produto) i.getSerializableExtra("produto");

        if (pro != null) {
            btnExcluir.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Editando " + pro.getNome(), Toast.LENGTH_SHORT).show();
            byte[] decodedString = Base64.decode(pro.getFoto(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imagem.setImageBitmap(decodedByte);
            imageSlct = true;
            edtNome.setText(pro.getNome());
            edtQtd.setText(Integer.toString(pro.getQuantidade()));
            status.setSelection((pro.getStatus().equals("C")) ? 1 : 0);
        } else {
            btnExcluir.setVisibility(View.GONE);
        }
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

    public void deletar(View v) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.removerProduto(pro);
        finish();
        Toast.makeText(CadastroProduto.this, "Produto removido", Toast.LENGTH_SHORT).show();
    }

    public void salvar(View v) {
        String mensagem = validarCampos();
        if (mensagem.equals("")) {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);

            if (pro == null) {
                Produto produto = new Produto();
                produto.setFoto(getImagem());
                produto.setNome(edtNome.getText().toString());
                produto.setQuantidade(Integer.parseInt(edtQtd.getText().toString()));
                produto.setStatus(status.getSelectedItem().toString().equals("COMPRADO") ? "C" : "N");
                databaseHelper.salvarProduto(produto);
            } else {
                pro.setFoto(getImagem());
                pro.setNome(edtNome.getText().toString());
                pro.setQuantidade(Integer.parseInt(edtQtd.getText().toString()));
                pro.setStatus(status.getSelectedItem().toString().equals("COMPRADO") ? "C" : "N");
                databaseHelper.update(pro);
            }
            finish();
        } else {
            mensagemErro(mensagem);
        }
    }

    public String getImagem() {
        Bitmap bitmap = ((BitmapDrawable) imagem.getDrawable()).getBitmap();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);

        byte[] byteArray = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(byteArray, Base64.DEFAULT);
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

    public void mensagemErro(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção");
        builder.setMessage(mensagem);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(CadastroProduto.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(CadastroProduto.this, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
}
