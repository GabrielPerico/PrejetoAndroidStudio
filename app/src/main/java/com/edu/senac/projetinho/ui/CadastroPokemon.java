package com.edu.senac.projetinho.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.senac.projetinho.R;
import com.edu.senac.projetinho.helper.DatabaseHelper;
import com.edu.senac.projetinho.model.Pokedex;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CadastroPokemon extends AppCompatActivity {

    Spinner tipo1, tipo2, preEvo, evo;
    TextView nome, id;
    ImageView imgPkm;
    Boolean imageSlct = false;
    Pokedex pkm;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pokemon);
        imgPkm = findViewById(R.id.imgPkm);
        nome = findViewById(R.id.nomePKM);
        id = findViewById(R.id.numeroPKM);
        tipo1 = findViewById(R.id.tipo1);
        tipo2 = findViewById(R.id.tipo2);
        preEvo = findViewById(R.id.preEvo);
        evo = findViewById(R.id.evo);
        btnDelete = findViewById(R.id.btnDelete);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        List<Pokedex> allPkm = databaseHelper.buscarTodosPkm();
        List<String> nomes=allPkm.stream().map(x->x.getNome()).collect(Collectors.<String>toList());
        nomes.add(0,"Nenhuma");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomes);
        preEvo.setAdapter(adapter);
        evo.setAdapter(adapter);

        Intent i = getIntent();
        pkm = (Pokedex) i.getSerializableExtra("pokemon");

        if (pkm != null) {
            btnDelete.setVisibility(View.VISIBLE);
            byte[] decodedString = Base64.decode(pkm.getImagem(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imgPkm.setImageBitmap(decodedByte);
            nome.setText(pkm.getNome());
            id.setText(Integer.toString(pkm.getNum()));
            for (Integer cnt = 1; cnt < tipo1.getAdapter().getCount(); cnt++) {
                if (pkm.getTipoPrim().equals(tipo1.getItemAtPosition(cnt))) {
                    tipo1.setSelection(cnt);
                }
            }
            for (Integer cnt = 1; cnt < tipo2.getAdapter().getCount(); cnt++) {
                if (pkm.getTipoSec().equals(tipo2.getItemAtPosition(cnt))) {
                    tipo2.setSelection(cnt);
                }
            }
            for (Integer cnt = 1; cnt < evo.getAdapter().getCount(); cnt++) {
                if (pkm.getEvo().equals(evo.getItemAtPosition(cnt))) {
                    evo.setSelection(cnt);
                }
            }
            for (Integer cnt = 1; cnt < preEvo.getAdapter().getCount(); cnt++) {
                if (pkm.getPreEvo().equals(preEvo.getItemAtPosition(cnt))) {
                    preEvo.setSelection(cnt);
                }
            }
            imageSlct = true;
        } else {
            btnDelete.setVisibility(View.GONE);
        }
    }

    public void pegarImagem(View v) {
        if (checkAndRequestPermissions()) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 1);
        }
    }

    public boolean checkAndRequestPermissions() {
        int files = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (files != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 1:
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        imgPkm.setImageBitmap(bitmap);
                        imageSlct = true;
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }
    }

    public String validarCampos() {
        if (!imageSlct) {
            Log.d("salvarPK", "Imagem não informada");
            return "O campo Imagem não foi informado!";
        }
        if (id.getText().toString().trim().length() == 0) {
            Log.d("salvarPK", "Número não informado");
            return "O campo Número não foi informado!";
        }
        if (nome.getText().toString().trim().length() == 0) {
            Log.d("salvarPK", "Nome não informado");
            return "O campo Nome não foi informado!";
        }
        if (tipo1.getSelectedItemPosition() == 0) {
            Log.d("salvarPK", "Tipo não informado");
            return "O campo Tipo não foi informado!";
        }
        return "";
    }

    public void salvarPkm(View v) {
        String mensagem = validarCampos();
        if (mensagem.equals("")) {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            if (pkm == null) {
                Pokedex pokemon = new Pokedex();
                pokemon.setImagem(getImagem());
                pokemon.setNum(Integer.parseInt(id.getText().toString()));
                pokemon.setNome(nome.getText().toString());
                pokemon.setTipoPrim(tipo1.getSelectedItem().toString());
                pokemon.setTipoSec((tipo2.getSelectedItemPosition() == 0) ? "" : tipo2.getSelectedItem().toString());
                pokemon.setPreEvo((preEvo.getSelectedItemPosition() == 0) ? "" : preEvo.getSelectedItem().toString());
                pokemon.setEvo((evo.getSelectedItemPosition() == 0) ? "" : evo.getSelectedItem().toString());
                databaseHelper.salvarPokemon(pokemon);
            } else {
                pkm.setImagem(getImagem());
                pkm.setNum(Integer.parseInt(id.getText().toString()));
                pkm.setNome(nome.getText().toString());
                pkm.setTipoPrim(tipo1.getSelectedItem().toString());
                pkm.setTipoSec((tipo2.getSelectedItemPosition() == 0) ? "" : tipo2.getSelectedItem().toString());
                pkm.setPreEvo((preEvo.getSelectedItemPosition() == 0) ? "" : preEvo.getSelectedItem().toString());
                pkm.setEvo((evo.getSelectedItemPosition() == 0) ? "" : evo.getSelectedItem().toString());
                databaseHelper.updatePkm(pkm);
            }
            finish();
        } else {
            mensagemErro(mensagem);
        }
    }
    public void deletarPkm(View v) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.removerPkm(pkm);
        finish();
        Toast.makeText(CadastroPokemon.this, "Pokemon removido", Toast.LENGTH_SHORT).show();
    }

    public void mensagemErro(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção");
        builder.setMessage(mensagem);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(CadastroPokemon.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(CadastroPokemon.this, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    public String getImagem() {
        Bitmap bitmap = ((BitmapDrawable) imgPkm.getDrawable()).getBitmap();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);

        byte[] byteArray = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}
