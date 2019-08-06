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
import com.edu.senac.projetinho.model.Produto;

import java.util.List;

public class AdapterList extends BaseAdapter {

    private final List<Produto> produtos;
    private final Activity activity;


    public AdapterList(List<Produto> produtos, Activity activity) {
        this.produtos = produtos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return produtos.get(position).getCodigo();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.produto_item, parent, false);
        Produto produto = produtos.get(position);
        TextView txNome = view.findViewById(R.id.nomeProduto);
        TextView txQtd = view.findViewById(R.id.qtdProduto);
        ImageView stat = view.findViewById(R.id.imgStatus);
        ImageView foto = view.findViewById(R.id.imgProduto);

        byte[] decodedString = Base64.decode(produto.getFoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        if(produto.getStatus().equals("C")){
            stat.setImageResource(R.drawable.checked);
        }else{
            stat.setImageResource(R.drawable.alert);
        }

        txNome.setText(produto.getNome());
        txQtd.setText(Integer.toString(produto.getQuantidade()));
        foto.setImageBitmap(decodedByte);

        return view;
    }

    public void atualizarProdutos(List<Produto> novosProdutos){
        this.produtos.clear();
        this.produtos.addAll(novosProdutos);
        notifyDataSetChanged();
    }
}
