package ltsolutions.latreta.pomodoroapp.Model.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ltsolutions.latreta.pomodoroapp.Model.Adapters.CategoriaAdapter;
import ltsolutions.latreta.pomodoroapp.Model.Categoria;
import ltsolutions.latreta.pomodoroapp.Model.Script.ScriptSQL;


public class categoryDAO extends DAO {

    public categoryDAO(Context context){
        DB_NAME = "CATEGORIA_DB";
        TABLE_NAME = "CATEGORIA";
        SCRIPT = ScriptSQL.generateCat();
        DB_VERSION = 1;
        helper = new SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
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


    public CategoriaAdapter buscarCategorias(Context context){
        CategoriaAdapter adaptador = new CategoriaAdapter(context, new ArrayList<Categoria>());
        Cursor c = load();
        if(c.getCount() > 0){
            c.moveToFirst();
            do{
                Categoria categoria = new Categoria();
                categoria.setId(c.getLong(0));
                categoria.setNome(c.getString(1));
                adaptador.add(categoria);

            }while(c.moveToNext());
        }
        return adaptador;
    }
}
