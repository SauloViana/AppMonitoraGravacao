package br.com.monitoragravacaoradio.Task;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import br.com.monitoragravacaoradio.GravacaoActivity;
import br.com.monitoragravacaoradio.WebClient;
import br.com.monitoragravacaoradio.modelo.Gravacao;

/**
 * Created by Saulo on 13/12/2016.
 */

public class StatusCurrentRecAsyncTask extends AsyncTask<Object, Object, String> {
    private final GravacaoActivity activity;
    private final Gravacao gravacao;

    public StatusCurrentRecAsyncTask(GravacaoActivity activity, Gravacao gravacao) {
        this.activity = activity;
        this.gravacao = gravacao;
    }

    @Override
    protected String doInBackground(Object... params) {
        WebClient webClient = new WebClient();
        return webClient.post();
    }

    @Override
    protected void onPostExecute(String resposta) {
        try {
            if (resposta.equals("0")) {
                gravacao.serverNotFound();
            } else {
                JSONObject json = new JSONObject(resposta);

                String fileName = json.getString("fileName");
                String fileDateModification = json.getString("fileDateMod").replaceAll("%20", " ");
                String filePath = json.getString("filePath");
                int fileSize = json.getInt("fileSize");

                gravacao.isRecording(fileName, fileSize, fileDateModification, filePath);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}