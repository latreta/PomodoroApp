package ltsolutions.latreta.pomodoroapp.View;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import ltsolutions.latreta.pomodoroapp.Controller.PlayActivityController;
import ltsolutions.latreta.pomodoroapp.Model.Adapters.TaskAdapter;
import ltsolutions.latreta.pomodoroapp.Model.Task;
import ltsolutions.latreta.pomodoroapp.Model.Timer;
import ltsolutions.latreta.pomodoroapp.R;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    private ImageButton btnPlay;
    private ImageButton btnPause;
    private ImageButton btnCancel;

    private Timer timer;

    private boolean isPlaying;
    private boolean isPaused;
    private boolean isStopped;

    private TextView textViewTimer;
    private TextView nameTarefaPlayer;

    private TaskAdapter adapter;

    private Task tarefa = null;

    private ListView listViewTarefaPlayer;

    private PlayActivityController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        isPlaying = isPaused = isStopped = false;

        btnPlay              = (ImageButton)findViewById(R.id.btnPlay);
        btnPause             = (ImageButton)findViewById(R.id.btnPause);
        btnCancel            = (ImageButton)findViewById(R.id.btnCancel);

        textViewTimer        = (TextView)findViewById(R.id.textViewTimer);
        nameTarefaPlayer     = (TextView)findViewById(R.id.nameTarefaPlayer);

        listViewTarefaPlayer = (ListView)findViewById(R.id.listViewTarefaPlayer);

        controller = new PlayActivityController(this);

        this.controller.preencher(nameTarefaPlayer,textViewTimer);

        adapter = this.controller.buscarTarefas(this);

        listViewTarefaPlayer.setAdapter(adapter);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        listViewTarefaPlayer.setOnItemClickListener(this);


    }

    @Override
    public void onClick(View v) {
        long remaining = 0;
        int op = 0;
       if(v == btnPlay) {
           if(timer != null) {
               if (isPlaying == false & isPaused == false) {
                   isPlaying = true;
                   timer.start();
               } else if (isPaused == true && isStopped == false) {
                   timer.start();
               }
               op = 0;
           }
       }
        if(v == btnPause){
            if(timer != null) {
                remaining = timer.onPause();
                timer.cancel();
                timer = new Timer(remaining, 1000, textViewTimer);
                isPaused = true;
                isPlaying = false;
                op = 1;
            }
        }
        if(v == btnCancel){
            if(timer != null){
                timer.cancel();
                timer = null;
                isPaused = false;
                isStopped = true;
                isPlaying = false;
                tarefa = null;
                this.controller.preencher(nameTarefaPlayer,textViewTimer);
                op = 2;
            }
        }
        this.controller.alert(op, this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        tarefa = adapter.getItem(position);
        isStopped = isPaused = isPlaying = false;
        if(timer != null){
            timer.cancel();
        }
        int tempo = tarefa.getDuracao()*60000;
        if(timer != null){
            timer.cancel();
        }
        if(tarefa != null){
            nameTarefaPlayer.setText(tarefa.getNome());
            timer = new Timer(tempo,1000,textViewTimer);
            if(tarefa.getDuracao() == 5){
                textViewTimer.setText("00:0"+tarefa.getDuracao()+":00");

            }else{
                textViewTimer.setText("00:"+tarefa.getDuracao()+":00");
            }
        }

    }
}
