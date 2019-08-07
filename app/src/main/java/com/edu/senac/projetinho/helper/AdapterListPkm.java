package com.edu.senac.projetinho.helper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
        switch (pokedex.getTipoPrim()){
            case "Inseto":
                tipo1.setBackgroundColor(Color.parseColor("#729F3F"));
                break;
            case "Dragão":
                tipo1.setBackgroundResource(R.drawable.gradient1);
                break;
            case "Fada":
                tipo1.setBackgroundColor(Color.parseColor("#FDB9E9"));
                break;
            case "Fogo":
                tipo1.setBackgroundColor(Color.parseColor("#FD7D24"));
                break;
            case "Fantasma":
                tipo1.setBackgroundColor(Color.parseColor("#7B62A3"));
                break;
            case "Terra":
                tipo1.setBackgroundResource(R.drawable.gradient2);
                break;
            case "Normal":
                tipo1.setBackgroundColor(Color.parseColor("#A4ACAF"));
                break;
            case "Psiquico":
                tipo1.setBackgroundColor(Color.parseColor("#F366B9"));
                break;
            case "Metal":
                tipo1.setBackgroundColor(Color.parseColor("#9EB7B8"));
                break;
            case "Escuridão":
                tipo1.setBackgroundColor(Color.parseColor("#707070"));
                break;
            case "Eletrico":
                tipo1.setBackgroundColor(Color.parseColor("#EED535"));
                break;
            case "Lutador":
                tipo1.setBackgroundColor(Color.parseColor("#D56723"));
                break;
            case "Voador":
                tipo1.setBackgroundResource(R.drawable.gradient3);
                break;
            case "Grama":
                tipo1.setBackgroundColor(Color.parseColor("#9BCC50"));
                break;
            case "Gelo":
                tipo1.setBackgroundColor(Color.parseColor("#51C4E7"));
                break;
            case "Veneno":
                tipo1.setBackgroundColor(Color.parseColor("#B97FC9"));
                break;
            case "Pedra":
                tipo1.setBackgroundColor(Color.parseColor("#A38C21"));
                break;
            case "Água":
                tipo1.setBackgroundColor(Color.parseColor("#4592C4"));
                break;
        }
        if(!pokedex.getTipoSec().equals("")){
            tipo2.setText(pokedex.getTipoSec());
            switch (pokedex.getTipoSec()){
                case "Inseto":
                    tipo2.setBackgroundColor(Color.parseColor("#729F3F"));
                    break;
                case "Dragão":
                    tipo2.setBackgroundResource(R.drawable.gradient1);
                    break;
                case "Fada":
                    tipo2.setBackgroundColor(Color.parseColor("#FDB9E9"));
                    break;
                case "Fogo":
                    tipo2.setBackgroundColor(Color.parseColor("#FD7D24"));
                    break;
                case "Fantasma":
                    tipo2.setBackgroundColor(Color.parseColor("#7B62A3"));
                    break;
                case "Terra":
                    tipo2.setBackgroundResource(R.drawable.gradient2);
                    break;
                case "Normal":
                    tipo2.setBackgroundColor(Color.parseColor("#A4ACAF"));
                    break;
                case "Psiquico":
                    tipo2.setBackgroundColor(Color.parseColor("#F366B9"));
                    break;
                case "Metal":
                    tipo2.setBackgroundColor(Color.parseColor("#9EB7B8"));
                    break;
                case "Escuridão":
                    tipo2.setBackgroundColor(Color.parseColor("#707070"));
                    break;
                case "Eletrico":
                    tipo2.setBackgroundColor(Color.parseColor("#EED535"));
                    break;
                case "Lutador":
                    tipo2.setBackgroundColor(Color.parseColor("#D56723"));
                    break;
                case "Voador":
                    tipo2.setBackgroundResource(R.drawable.gradient3);
                    break;
                case "Grama":
                    tipo2.setBackgroundColor(Color.parseColor("#9BCC50"));
                    break;
                case "Gelo":
                    tipo2.setBackgroundColor(Color.parseColor("#51C4E7"));
                    break;
                case "Veneno":
                    tipo2.setBackgroundColor(Color.parseColor("#B97FC9"));
                    break;
                case "Pedra":
                    tipo2.setBackgroundColor(Color.parseColor("#A38C21"));
                    break;
                case "Água":
                    tipo2.setBackgroundColor(Color.parseColor("#4592C4"));
                    break;
            }
        }
        return view;
    }
    public void atualizarPokemon(List<Pokedex> novosPokemons){
        this.pokemons.clear();
        this.pokemons.addAll(novosPokemons);
        notifyDataSetChanged();
    }
}
