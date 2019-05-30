package br.com.monitoragravacaoradio;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Saulo on 13/12/2016.
 */

public class WebClient {
    public String post()
    {
        String resposta;
        int codeResp = 0;

        try {
            //URL url = new URL("http://sauloviana.ddns.net:8080/soapws/status_current_rec.php");
            URL url = new URL("http://192.168.15.4:8080/soapws/status_current_rec.php");
            //Abre a conexão com a url
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //Seta os parâmetros
            connection.setUseCaches(false);
            connection.setConnectTimeout(5000);
            //Atribui o código de status da conexão
            codeResp = connection.getResponseCode();

            try {
                if (codeResp >= 200 && codeResp < 300) {
                    //Recebe a resposta da url
                    Scanner scanner = new Scanner(connection.getInputStream());
                    //Atribui a resposta
                    resposta = scanner.next();
                    //Retorna a resposta
                    return resposta;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //Fecha a conexão
                connection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.valueOf(codeResp);
    }
}
