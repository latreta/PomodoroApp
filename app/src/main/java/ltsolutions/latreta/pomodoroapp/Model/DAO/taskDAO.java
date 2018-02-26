package ltsolutions.latreta.pomodoroapp.Model.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ltsolutions.latreta.pomodoroapp.Model.Adapters.TaskAdapter;
import ltsolutions.latreta.pomodoroapp.Model.Categoria;
import ltsolutions.latreta.pomodoroapp.Model.Script.ScriptSQL;
import ltsolutions.latreta.pomodoroapp.Model.Task;

/**
 * Created by LaTreTz on 27/05/2016.
 */
public class taskDAO extends DAO {


    public taskDAO(Context context){

        DB_NAME = "TAREFA_DB";
        TABLE_NAME = "TAREFA";
        SCRIPT = ScriptSQL.generate();
        DB_VERSION = 1;
        helper = new SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(SCRIPT);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };
        database = helper.getWritableDatabase();

    }


    public TaskAdapter loadTarefas(Context context){
        TaskAdapter adaptador = new TaskAdapter(context,new ArrayList<Task>());

        Cursor c = load();
        if(c.getCount() > 0){
            c.moveToFirst();
            do{
               Task tarefa = new Task();
                Categoria categoria = new Categoria();
                tarefa.setId(c.getLong(0));
                tarefa.setNome(c.getString(1));
                categoria.setNome(c.getString(2));
                tarefa.setCategoria(categoria);
                tarefa.setTempo(c.getInt(3));
                adaptador.add(tarefa);
            }while(c.moveToNext());
        };
        return adaptador;
    }
}
