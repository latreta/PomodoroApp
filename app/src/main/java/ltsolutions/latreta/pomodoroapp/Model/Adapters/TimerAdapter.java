package ltsolutions.latreta.pomodoroapp.Model.Adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by LaTreTz on 09/05/2016.
 */
public class TimerAdapter extends ArrayAdapter<String> {
    public TimerAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
