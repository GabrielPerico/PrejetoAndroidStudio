package com.edu.senac.projetinho.helper;

import android.content.Context;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.edu.senac.projetinho.model.Pokedex;
import com.edu.senac.projetinho.model.Produto;
import com.edu.senac.projetinho.model.Usuario;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.query.In;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String NOME_BANCO = "ferramentas.db";
    private static final Integer VERSAO = 1;

    private Dao<Usuario, Integer> usuarioDao = null;
    private Dao<Produto, Integer> produtoDao = null;
    private Dao<Pokedex, Integer> pokemonDao = null;

    public DatabaseHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Usuario.class);
            TableUtils.createTable(connectionSource, Produto.class);
            TableUtils.createTable(connectionSource, Pokedex.class);

            Usuario usuario = new Usuario();
            usuario.setEmail("aluno@senac.br");
            usuario.setSenha("senha");
            getUsuarioDao().create(usuario);


        } catch (Exception e) {
            Log.e("banco", "Erro ao criar banco");
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Usuario.class, true);
            TableUtils.dropTable(connectionSource, Produto.class, true);
            TableUtils.dropTable(connectionSource, Pokedex.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (Exception e) {
            Log.e("banco", "Erro ao criar banco");
        }
    }

    public Dao<Usuario, Integer> getUsuarioDao() throws SQLException {
        if (usuarioDao == null) {
            usuarioDao = getDao(Usuario.class);
        }
        return usuarioDao;
    }

    public Usuario validarUsuario(String email, String senha) {
        try {
            Usuario usuario = getUsuarioDao().queryBuilder().where().eq("email", email).and().eq("senha", senha).queryForFirst();
            return usuario;
        } catch (Exception e) {
            Log.e("banco", "Falha ao recuperar usuario");
        }
        return null;
    }

    public Dao<Produto, Integer> getProdutoDao() throws SQLException {
        if (produtoDao == null) {
            produtoDao = getDao(Produto.class);
        }
        return produtoDao;
    }

    public void salvarProduto(Produto produto) {
        try {
            int id = getProdutoDao().create(produto);
            Log.d("banco", "Total :" + getProdutoDao().countOf());
        } catch (Exception e) {
            Log.e("banco", "Falha ao salvar produto");
        }
    }

    public void removerProduto(Produto produto){
        try{
            getProdutoDao().delete(produto);
        }catch (Exception e){
            Log.e("banco","Falha ao remover produto");
        }
    }

    public void update (Produto produto){
        try {
            getProdutoDao().update(produto);
        }catch (Exception e){
            Log.e("banco","Falha ao atualizar produto");
        }
    }

    public List<Produto> buscarTodos(){
        List<Produto> produtos = null;
        try{
            return getProdutoDao().queryBuilder().query();
        }catch (Exception e){
            Log.e("banco","Falha ao buscar produtos");
        }
        return new ArrayList<>();
    }

    public Dao<Pokedex, Integer> getPokemonDao() throws SQLException {
        if (pokemonDao == null) {
            pokemonDao = getDao(Pokedex.class);
        }
        return pokemonDao;
    }

    public void salvarPokemon(Pokedex pokedex){
        try {
            int id = getPokemonDao().create(pokedex);
            Log.d("banco", "Total de pkm :" + getPokemonDao().countOf());
        }catch (Exception e){
            Log.e("banco", "Falha ao salvar pokemon");
        }
    }
    public List<Pokedex> buscarTodosPkm(){
        List<Pokedex> pokedex = null;
        try{
            return getPokemonDao().queryBuilder().query();
        }catch (Exception e){
            Log.e("banco","Falha ao buscar pokemons");
        }
        return new ArrayList<>();
    }
}
