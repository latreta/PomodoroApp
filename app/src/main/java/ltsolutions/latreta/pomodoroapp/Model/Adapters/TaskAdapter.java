package ltsolutions.latreta.pomodoroapp.Model.Adapters;

import android.content.*;
import android.view.*;
import android.widget.*;


import java.util.ArrayList;

import ltsolutions.latreta.pomodoroapp.Model.Task;
import ltsolutions.latreta.pomodoroapp.R;

/**
 * Created by LaTreTz on 16/05/2016.
 */
public class TaskAdapter extends ArrayAdapter<Task>{
        private Context context;
        private ArrayList<Task> listaTarefas;

    public TaskAdapter(Context context, ArrayList<Task> tarefas){
        super(context,0,tarefas);
        this.listaTarefas = tarefas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task tarefa = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listviewtarefa,parent,false);
        }
        TextView nome = (TextView)convertView.findViewById(R.id.nomeTarefa);
        TextView categoria = (TextView)convertView.findViewById(R.id.categoryId);
        TextView tempo = (TextView)convertView.findViewById(R.id.timeDuration);

        nome.setText(tarefa.getNome());
        categoria.setText(tarefa.getCategoria().getNome());
        tempo.setText(String.valueOf(tarefa.getDuracao()) + " minutos");
        return convertView;
    }


}
