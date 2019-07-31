package com.edu.senac.projetinho.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.query.In;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable
public class Produto implements Serializable {
    @DatabaseField(generatedId = true)
    private Integer codigo;

    @DatabaseField(canBeNull = false)
    private String nome;

    @DatabaseField(canBeNull = false)
    private Integer quantidade;

    @DatabaseField(canBeNull = false)
    private String foto;

    @DatabaseField(canBeNull = false)
    private String status;


}
