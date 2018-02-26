package ltsolutions.latreta.pomodoroapp.Model;

import android.app.AlertDialog;
import android.os.CountDownTimer;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;


public class Timer extends CountDownTimer {
    private TextView textView;
    private long remaining;

    public Timer(long millisInFuture, long countDownInterval, TextView t){
        super(millisInFuture,countDownInterval);
        textView = t;

    }
    @Override
    public void onTick(long millisUntilFinished) {
        long millis = millisUntilFinished;
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        System.out.println(hms);
        textView.setText(hms);
        remaining = millis;
    }

    public void onResume(long millisUntilFinished, TextView timer){
        long millis = millisUntilFinished;
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        System.out.println(hms);
        timer.setText(hms);
    }

    @Override
    public void onFinish() {
        textView.setText("COMPLETADO");
        textView.setText("00:00:00");
    }

    public long onPause(){
        return this.remaining;
    }
}
