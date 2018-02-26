package ltsolutions.latreta.pomodoroapp.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.*;
import android.support.v4.app.AppLaunchChecker;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import ltsolutions.latreta.pomodoroapp.Model.Adapters.*;
import ltsolutions.latreta.pomodoroapp.Model.Categoria;
import ltsolutions.latreta.pomodoroapp.Model.DAO.*;
import ltsolutions.latreta.pomodoroapp.Model.Task;
import ltsolutions.latreta.pomodoroapp.R;


public class HomeActivityController {

    private Context ctx;
    private taskDAO database;
    private categoryDAO databaseCat;

    public HomeActivityController(Context context){
        this.ctx = context;
        this.database = new taskDAO(context);
        this.databaseCat = new categoryDAO(context);
    }


    public CategoriaAdapter buscarCategorias(Context context){
        return databaseCat.buscarCategorias(context);
    }


    public TaskAdapter buscarTarefas(Context context){
        return database.loadTarefas(context);
    }

    public void deleteTarefa(Task tarefa){
        tarefa.delete(database);
    }

    public Dialog fillDialog(Context context){
        final Dialog dialog = new Dialog(context);
        CategoriaAdapter adapter = buscarCategorias(context);

        dialog.setContentView(LayoutInflater.from(context).inflate(R.layout.listdialogcat,null));
        dialog.setTitle("Categoria:");

        TextView dialogNomeCategoria   = (TextView)dialog.findViewById(R.id.dialogNomeCategoria);
        final EditText dialogEditNome        = (EditText)dialog.findViewById(R.id.dialogEditNome);
        ListView dialogListaCategorias = (ListView)dialog.findViewById(R.id.dialogListaCategorias);
        Button dialogBtnSalvarCategorias = (Button)dialog.findViewById(R.id.dialogBtnSalvarCategoria);

        dialogNomeCategoria.setText("Categoria");
        dialogEditNome.setText("");


        dialogListaCategorias.setAdapter(adapter);


        dialogBtnSalvarCategorias.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Categoria categoria = new Categoria();
                if(!dialogEditNome.getText().toString().isEmpty()) {
                    categoria.setNome(dialogEditNome.getText().toString());
                    categoria.add(databaseCat);
                    dialog.dismiss();
                }

            }
        });
        dialog.setCancelable(true);
        return dialog;
    }




}
