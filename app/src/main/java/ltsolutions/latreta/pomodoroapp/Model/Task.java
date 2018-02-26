package ltsolutions.latreta.pomodoroapp.Model;

import android.content.ContentValues;
import android.content.Context;

import java.io.Serializable;

import ltsolutions.latreta.pomodoroapp.Model.DAO.taskDAO;

/**
 * Created by LaTreTz on 06/05/2016.
 */
public class Task implements Serializable {
    private long id;
    private String nome;
    private int tempo;
    private Categoria categoria;

    public Task(final String nome, final Categoria categoria, final int tempo) {
        this.id = 0;
        this.nome = nome;
        this.categoria = categoria;
        this.tempo = tempo;
    }

    public Task(){
        this.id = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempo() {
        return tempo;
    }

    public int getDuracao(){
        return (tempo+1)*5;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getCat(){
        return (int) categoria.getId();
    }

    public String toString(){
        return nome + " - " + getCategoria().getNome() + " - " + getDuracao() + " minutos";
    }

    public void add(taskDAO db){

        ContentValues data = new ContentValues();
        data.put("NOME", this.nome);
        data.put("CATEGORIA", this.categoria.getNome());
        data.put("DURACAO", this.tempo);
        db.adicionar(data);

    }
    public void update(taskDAO db){

        ContentValues data = new ContentValues();
        data.put("NOME", this.nome);
        data.put("CATEGORIA", this.categoria.getNome());
        data.put("DURACAO", this.tempo);
        db.modificar(data,this.id);

    }
    public void delete(taskDAO db){
        db.remover(this.id);
    }

}
