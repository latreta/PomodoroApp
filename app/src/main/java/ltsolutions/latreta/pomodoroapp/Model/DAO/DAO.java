package ltsolutions.latreta.pomodoroapp.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LaTreTz on 27/05/2016.
 */
public abstract class DAO implements dataAcessObject {
    public String DB_NAME;
    public String TABLE_NAME;
    public String SCRIPT;
    public int DB_VERSION;

    public SQLiteDatabase database;
    public SQLiteOpenHelper helper;

    public void adicionar(ContentValues data){
        database.insert(TABLE_NAME, null, data);
    }
    public void modificar(ContentValues data, long id){
        database.update(TABLE_NAME,data," _id= ? ", new String[]{ String.valueOf(id)});
    }
    public void remover(long id){
        database.delete(TABLE_NAME," _id= ? ", new String[]{ String.valueOf(id)});
    }
    public Cursor load(){
        return database.query(TABLE_NAME,null,null,null,null,null,null);
    }
}
