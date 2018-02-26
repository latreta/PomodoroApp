package ltsolutions.latreta.pomodoroapp.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;

import ltsolutions.latreta.pomodoroapp.Controller.HomeActivityController;
import ltsolutions.latreta.pomodoroapp.Model.Adapters.CategoriaAdapter;
import ltsolutions.latreta.pomodoroapp.Model.Adapters.TaskAdapter;
import ltsolutions.latreta.pomodoroapp.Model.Task;
import ltsolutions.latreta.pomodoroapp.R;

public class HomeActivity extends AppCompatActivity implements AbsListView.OnItemClickListener{

    private ListView listViewTarefa;
    private Spinner spinnerCategoria;
    private HomeActivityController controller;
    private TaskAdapter adpTarefas;
    private CategoriaAdapter adpCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);;
        setContentView(R.layout.activity_home);

        this.controller = new HomeActivityController(this);

        spinnerCategoria  = (Spinner)findViewById(R.id.spinnerCategoria);

        listViewTarefa    = (ListView)findViewById(R.id.listViewTarefa);

        this.refreshActivity();

    }


    @Override
    protected void onResume() {
        super.onResume();
        this.refreshActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem Item){
        Intent it;
        int id = Item.getItemId();
        switch(id){
            case R.id.add_Task:
                it = new Intent(this, TaskActivity.class);
                startActivityForResult(it, 1);
            break;
            case R.id.play_task:
                it = new Intent(this, PlayActivity.class);
                startActivityForResult(it, 2);
                break;
            case R.id.new_category:
                Dialog dialog = this.controller.fillDialog(this);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        refreshActivity();
                    }
                });
                break;
            default:
                break;
        }
        return true;
    }


    private void refreshActivity(){

        this.adpTarefas = this.controller.buscarTarefas(this);

        this.adpCategoria = this.controller.buscarCategorias(this);
        this.adpCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinnerCategoria.setAdapter(adpCategoria);
        this.listViewTarefa.setAdapter(adpTarefas);

        this.listViewTarefa.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Task tarefa = adpTarefas.getItem(position);
        final Intent it = new Intent(this, TaskActivity.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Qual ação deseja realizar?").setTitle("A tarefa "+tarefa.getNome() + " foi selecionada");


        if(parent == listViewTarefa) {

            builder.setNeutralButton("Alterar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    it.putExtra("TAREFA", tarefa);
                    startActivityForResult(it, 0);
                }
            });

            builder.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    controller.deleteTarefa(tarefa);
                    adpTarefas = controller.buscarTarefas(getApplicationContext());
                    listViewTarefa.setAdapter(adpTarefas);
                }
            });
            AlertDialog dlg = builder.create();
            dlg.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adpTarefas = this.controller.buscarTarefas(this);
        listViewTarefa.setAdapter(adpTarefas);
    }
}
