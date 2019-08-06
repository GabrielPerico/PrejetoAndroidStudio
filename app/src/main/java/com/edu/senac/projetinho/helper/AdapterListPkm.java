package com.edu.senac.projetinho.helper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.senac.projetinho.R;
import com.edu.senac.projetinho.model.Pokedex;

import java.util.List;

public class AdapterListPkm extends BaseAdapter {


    private final List<Pokedex> pokemons;
    private final Activity activity;

    public AdapterListPkm(List<Pokedex> pokemons,Activity activity){
        this.activity = activity;
        this.pokemons = pokemons;
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Object getItem(int position) {
        return pokemons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pokemons.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.pokemon_item, parent, false);
        Pokedex pokedex = pokemons.get(position);
        ImageView imgPkm = view.findViewById(R.id.imgPkm);
        TextView num = view.findViewById(R.id.numPkdx);
        TextView nome = view.findViewById(R.id.namePkm);
        TextView tipo1 = view.findViewById(R.id.tipo1);
        TextView tipo2 = view.findViewById(R.id.tipo2);


        byte[] decodedString = Base64.decode(pokedex.getImagem(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imgPkm.setImageBitmap(decodedByte);
        num.setText(Integer.toString(pokedex.getNum()));
        nome.setText(pokedex.getNome());
        tipo1.setText(pokedex.getTipoPrim());
        if(!pokedex.getTipoSec().equals("")){
            tipo2.setText(pokedex.getTipoSec());
        }
        return view;
    }
    public void atualizarPokemon(List<Pokedex> novosPokemons){
        this.pokemons.clear();
        this.pokemons.addAll(novosPokemons);
        notifyDataSetChanged();
    }
}
