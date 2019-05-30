package br.com.monitoragravacaoradio;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import br.com.monitoragravacaoradio.Task.StatusCurrentRecTimerTask;
import br.com.monitoragravacaoradio.modelo.Gravacao;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class GravacaoActivity extends AppCompatActivity {
    public GifDrawable gifDrawable;
    public static StatusCurrentRecTimerTask timerTask;
    public boolean running = false;
    private Gravacao gravacao;
    public TextView tvFilename, tvDate, tvPath, tvFilesize;
    public TextSwitcher tsStatus;
    public Button btnIniciaMonitoramento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravacao);

        final GifImageView gifImageView = (GifImageView) findViewById(R.id.gravacao_gif_gravando);
        tvFilename = (TextView) findViewById(R.id.gravacao_filename);
        tvDate = (TextView) findViewById(R.id.gravacao_date_modification);
        tvPath = (TextView) findViewById(R.id.gravacao_filepath);
        tvFilesize = (TextView) findViewById(R.id.gravacao_filesize);
        gifDrawable = (GifDrawable) gifImageView.getDrawable();
        tsStatus = (TextSwitcher) findViewById(R.id.gravacao_status);

        tsStatus.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView t = new TextView(GravacaoActivity.this);
                t.setGravity(Gravity.CENTER_HORIZONTAL);
                t.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
                t.setTextColor(Color.parseColor("#1E6912"));
                return t;
            }
        });
        tsStatus.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));

        gifStop();

        btnIniciaMonitoramento = (Button) findViewById(R.id.gravacao_btn_inicia_monitoramento);

        btnIniciaMonitoramento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running) {
                    gravacao.stopRec();
                } else {
                    if (gravacao == null) {
                        gravacao = new Gravacao(GravacaoActivity.this);
                    }
                    timerTask = new StatusCurrentRecTimerTask(GravacaoActivity.this, gravacao);
                    gravacao.startRec();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void gifStop() {
        gifDrawable.stop();
    }

    public void gifStart() {
        gifDrawable.start();
    }

    public boolean gifRunning() {
        return gifDrawable.isRunning();
    }
}
