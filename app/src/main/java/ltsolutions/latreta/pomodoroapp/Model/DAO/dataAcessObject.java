package ltsolutions.latreta.pomodoroapp.Model.DAO;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by LaTreTz on 27/05/2016.
 */
public interface dataAcessObject {
    public void adicionar(ContentValues data);
    public void modificar(ContentValues data, long id);
    public void remover(long id);
    public Cursor load();
}
