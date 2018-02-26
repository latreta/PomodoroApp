package ltsolutions.latreta.pomodoroapp.Model;

import android.content.ContentValues;

import java.io.Serializable;

import ltsolutions.latreta.pomodoroapp.Model.DAO.categoryDAO;

/**
 * Created by LaTreTz on 27/05/2016.
 */
public class Categoria implements Serializable {
    private String nome;
    private long id;
    private int pos;

    public Categoria(){
    }

    public Categoria(String nome){
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString(){
        return nome;
    }

    public void add(categoryDAO db){
        ContentValues data = new ContentValues();
        data.put("NOME", this.nome);
        db.adicionar(data);
    }

    public void remove(categoryDAO db){
        db.remover(this.id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
