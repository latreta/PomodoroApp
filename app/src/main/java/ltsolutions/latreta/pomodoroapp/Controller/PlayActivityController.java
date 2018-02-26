package ltsolutions.latreta.pomodoroapp.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.widget.TextView;

import java.util.ArrayList;

import ltsolutions.latreta.pomodoroapp.Model.Adapters.TaskAdapter;
import ltsolutions.latreta.pomodoroapp.Model.Categoria;
import ltsolutions.latreta.pomodoroapp.Model.DAO.taskDAO;
import ltsolutions.latreta.pomodoroapp.Model.Task;
import ltsolutions.latreta.pomodoroapp.Model.Timer;

/**
 * Created by LaTreTz on 17/05/2016.
 */
public class PlayActivityController {
    private Context ctx;
    private taskDAO database;
    private Timer timer;

    public PlayActivityController(Context context){
        this.ctx = context;
        this.database = new taskDAO(context);
    }

    public void preencher(TextView tarefa, TextView timer){
        tarefa.setText("Nenhuma Tarefa Selecionada");
        timer.setText("00:00:00");
    }

    public TaskAdapter buscaTarefas(Context context){
        return database.loadTarefas(context);
    }

    public void play(){
        this.timer.start();
    }

    public void fillPlayer(String nome, int tempo, TextView textTimer, TextView name){
        tempo = tempo+1;
        int duracao = tempo*60000;
        name.setText(nome);
        timer = new Timer(tempo,1000,textTimer);
        if(tempo == 5){
            textTimer.setText("00:0"+duracao+":00");
        }else{
            textTimer.setText("00:"+duracao+":00");
        }
    }

    public TaskAdapter buscarTarefas(Context context){
        return database.loadTarefas(context);
    }


    public void alert(int opcao, Context context){
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setNeutralButton("OK", null);
        switch(opcao){
            case 1:
                dlg.setMessage("Tarefa Pausada.");
                dlg.show();
                break;
            case 2:
                dlg.setMessage("Tarefa interrompida.");
                dlg.show();
                break;
            case 0:
                break;
            default:
                break;
        }
    }
}
