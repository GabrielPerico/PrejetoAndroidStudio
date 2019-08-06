package com.edu.senac.projetinho.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable
public class Pokedex implements Serializable {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false)
    private Integer num;

    @DatabaseField(canBeNull = false)
    private String nome;

    @DatabaseField(canBeNull = false)
    private String tipoPrim;

    @DatabaseField
    private String tipoSec;

    @DatabaseField(canBeNull = false)
    private String imagem;

    @DatabaseField
    private String preEvo;

    @DatabaseField
    private String evo;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoPrim() {
        return tipoPrim;
    }

    public void setTipoPrim(String tipoPrim) {
        this.tipoPrim = tipoPrim;
    }

    public String getTipoSec() {
        return tipoSec;
    }

    public void setTipoSec(String tipoSec) {
        this.tipoSec = tipoSec;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getPreEvo() {
        return preEvo;
    }

    public void setPreEvo(String preEvo) {
        this.preEvo = preEvo;
    }

    public String getEvo() {
        return evo;
    }

    public void setEvo(String evo) {
        this.evo = evo;
    }

}
