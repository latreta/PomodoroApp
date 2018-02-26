package ltsolutions.latreta.pomodoroapp.Controller;

import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

import ltsolutions.latreta.pomodoroapp.Model.Adapters.CategoriaAdapter;
import ltsolutions.latreta.pomodoroapp.Model.Adapters.TaskAdapter;
import ltsolutions.latreta.pomodoroapp.Model.Categoria;
import ltsolutions.latreta.pomodoroapp.Model.DAO.categoryDAO;
import ltsolutions.latreta.pomodoroapp.Model.DAO.taskDAO;
import ltsolutions.latreta.pomodoroapp.Model.Task;

/**
 * Created by LaTreTz on 07/05/2016.
 */
public class TaskActivityController {

    private taskDAO database;
    private categoryDAO databaseCat;


    public TaskActivityController(Context context){
        this.database = new taskDAO(context);
        this.databaseCat = new categoryDAO(context);
    }


    public void manageTarefa(Task tarefa){
        if(tarefa.getNome().isEmpty()){
            return;
        }else{
            if(tarefa.getId() == 0){
                adicionarTarefa(tarefa);
            }else{
                modificarTarefa(tarefa);
            }
        }
    }

    private void adicionarTarefa(Task tarefa){
        tarefa.add(database);
    }

    private void modificarTarefa(Task tarefa){
        tarefa.update(database);
    }


    public CategoriaAdapter buscarCategorias(Context context){
        return databaseCat.buscarCategorias(context);
    }

    public ArrayAdapter<String> buscaTimer(Context context){
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item);
        adaptador.add("5 minutos");
        adaptador.add("10 minutos");
        adaptador.add("15 minutos");
        adaptador.add("20 minutos");
        adaptador.add("25 minutos");
        adaptador.add("30 minutos");
        adaptador.add("40 minutos");
        adaptador.add("50 minutos");
        adaptador.add("60 minutos");
 //     itemselected + 1 * 5 minutos
        return adaptador;
    }

    public Categoria findNameCat(Context context, int id){
        CategoriaAdapter adapter = buscarCategorias(context);
        return adapter.getItem(id);
    }


}
