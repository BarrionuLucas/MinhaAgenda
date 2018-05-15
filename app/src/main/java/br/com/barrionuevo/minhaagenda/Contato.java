package br.com.barrionuevo.minhaagenda;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Lucas on 25/11/2017.
 */

class Contato{

    private int idContato;
    private String nome;
    private String telefone;

    public Contato() {
    }

    public Contato(String nome, int idContato, String telefone) {
        this.nome = nome;
        this.idContato = idContato;
        this.telefone = telefone;
    }

    public Contato(Contato value) {
        this.nome = value.nome;

        this.telefone = value.telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdContato() {
        return idContato;
    }

    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
