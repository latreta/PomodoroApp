package ltsolutions.latreta.pomodoroapp.View;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import ltsolutions.latreta.pomodoroapp.Controller.TaskActivityController;
import ltsolutions.latreta.pomodoroapp.Model.Adapters.CategoriaAdapter;
import ltsolutions.latreta.pomodoroapp.Model.Categoria;
import ltsolutions.latreta.pomodoroapp.Model.Task;
import ltsolutions.latreta.pomodoroapp.R;


public class TaskActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave;

    private EditText editTextNome;

    private Spinner spinnerCategoria;
    private Spinner spinnerTempo;

    private TaskActivityController controller;

    private CategoriaAdapter adpCategoria;
    private ArrayAdapter<String> adpTimer;

    private Task tarefa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        btnSave          = (Button)findViewById(R.id.btnSave);

        editTextNome     = (EditText)findViewById(R.id.editTextNome);

        spinnerCategoria = (Spinner)findViewById(R.id.spinnerCategoria);
        spinnerTempo     = (Spinner)findViewById(R.id.spinnerTempo);

        controller       = new TaskActivityController(this);

        btnSave.setOnClickListener(this);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("TAREFA")) {

            tarefa = (Task) bundle.getSerializable("TAREFA");
            preencher(tarefa);

        } else {

            tarefa = new Task();

        }

        adpCategoria = this.controller.buscarCategorias(this);
        adpCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTimer     = this.controller.buscaTimer(this);

        spinnerCategoria.setAdapter(adpCategoria);
        spinnerTempo.setAdapter(adpTimer);

    }

    public void preencher(Task tarefa){
        editTextNome.setText(tarefa.getNome());
        spinnerCategoria.setSelection((int)tarefa.getCategoria().getId());
        spinnerTempo.setSelection(tarefa.getTempo());
    }

    public void salvar() {
        try {
            tarefa.setNome(editTextNome.getText().toString());
            Categoria categoria = this.controller.findNameCat(this,spinnerCategoria.getSelectedItemPosition());
            categoria.setPos(spinnerCategoria.getSelectedItemPosition());
            tarefa.setCategoria(categoria);
            tarefa.setTempo(spinnerTempo.getSelectedItemPosition());
            this.controller.manageTarefa(tarefa);

        } catch (Exception ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao inserir os dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == btnSave){
            salvar();
            finish();
        }
    }
}
