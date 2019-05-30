package br.com.monitoragravacaoradio.Task;

import android.os.AsyncTask;

import java.util.TimerTask;
import br.com.monitoragravacaoradio.GravacaoActivity;
import br.com.monitoragravacaoradio.modelo.Gravacao;


/**
 * Created by Saulo on 13/12/2016.
 */

public class StatusCurrentRecTimerTask extends TimerTask {
    private GravacaoActivity activity;
    private StatusCurrentRecAsyncTask aTask;
    private Gravacao gravacao;

    public StatusCurrentRecTimerTask(GravacaoActivity activity, Gravacao gravacao) {
        this.activity = activity;
        this.gravacao = gravacao;
    }

    @Override
    public void run() {
        if (aTask == null || (activity.running && (aTask.getStatus() != AsyncTask.Status.RUNNING))) {
            aTask = new StatusCurrentRecAsyncTask(activity, gravacao);
            aTask.execute();
        }
    }
}