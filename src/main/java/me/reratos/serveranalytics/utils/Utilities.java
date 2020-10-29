package me.reratos.serveranalytics.utils;

import java.net.HttpURLConnection;
import java.net.URL;

public class Utilities {
    public static String getExternalAddress(){

        String ip = null;
        try
        {
            URL url = new URL("http://checkip.dyndns.org/");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.connect();
            java.io.BufferedReader pagina = new java.io.BufferedReader(new java.io.InputStreamReader(conexao.getInputStream()));
            ip = pagina.readLine();
            ip = ip.substring(ip.indexOf(": ") + 2, ip.lastIndexOf("</body>"));

            pagina.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return ip;
    }
}
