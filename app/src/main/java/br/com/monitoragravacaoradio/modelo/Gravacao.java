package br.com.monitoragravacaoradio.modelo;

import java.util.Timer;

import br.com.monitoragravacaoradio.GravacaoActivity;

/**
 * Created by Saulo on 16/12/2016.
 */

public class Gravacao {
    private static int REC_STATUS_ID;
    private GravacaoActivity activity;
    private String filename;
    private String dateModification;
    private int size = 0;
    private String pathFile;
    private static Timer timer;

    public Gravacao(GravacaoActivity activity) {
        this.activity = activity;
    }

    private String getFilename() {
        return filename;
    }

    private void setFilename(String filename) {
        this.filename = filename;
    }

    private String getDateModification() {
        return dateModification;
    }

    private void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }

    private int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    private String getPathFile() {
        return pathFile;
    }

    private void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }


    private void setRecInfo() {
        activity.tsStatus.setText(getStatusString());
        activity.tvFilename.setText(getFilename());
        activity.tvDate.setText(getDateModification());
        activity.tvPath.setText(getPathFile());
        activity.tvFilesize.setText(String.valueOf(getSize()) + " Kb");
    }

    public void isRecording(String fileName, int fileSize, String fileDateModification, String filePath) {
        if (getSize() < fileSize) {

            REC_STATUS_ID = 1;

            if (!activity.gifRunning()) {
                activity.gifStart();
            }

            setSize(fileSize);
            setFilename(fileName);
            setDateModification(fileDateModification);
            setPathFile(filePath);

            setRecInfo();
        } else {
            isNotRecording();
        }
    }

    public void stopRec() {
        activity.running = false;
        timer.cancel();
        activity.gifStop();
        activity.btnIniciaMonitoramento.setText("Iniciar Monitoramento");
        blankRecInfo();
    }

    public void startRec() {
        activity.running = true;
        timer = new Timer();
        timer.schedule(activity.timerTask, 100, 3000);
        activity.btnIniciaMonitoramento.setText("Parar Monitoramento");
    }

    private void isNotRecording() {
        REC_STATUS_ID = 2;
        setRecInfo();
    }

    public void serverNotFound() {
        REC_STATUS_ID = 3;
        activity.gifStop();
        setRecInfo();
    }

    private void blankRecInfo() {
        activity.tsStatus.setText("");
        activity.tvFilename.setText("");
        activity.tvDate.setText("");
        activity.tvPath.setText("");
        activity.tvFilesize.setText("");
    }

    private String getStatusString() {
        String statusString = "";

        switch (REC_STATUS_ID) {
            case 1:
                statusString = "Gravando...";
                break;
            case 2:
                statusString = "Parado!";
                break;
            case 3:
                statusString = "Servidor não encontrado!";
                break;
        }

        return statusString;
    }
}
