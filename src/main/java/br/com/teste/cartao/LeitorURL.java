package br.com.teste.cartao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class LeitorURL {

    public static void main(String[] args) throws IOException {
            //String url = "https://mqlinuxext.serasa.com.br/Homologa/consultahttps?p=71645060AdvxH19@        B49C      000002220000154JC     FI                   S99SINIAN                               N                                                                                                                                                                                                                                                                                                                  P002RSPU                                                                                                           I00100R                                                                                                            T999";
        	String urlParameters = "71645060AdvxH19@        B49C      000000000000272FC     FI                   S99SINIAN                               N                                                                                                                                                                                                                                                                                                                  P002RSPU                                                                                                           I00100R                                                                                                            T999";
            URL url = new URL("https://mqlinuxext.serasa.com.br/Homologa/consultahttps");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(urlParameters);

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());

    }

}