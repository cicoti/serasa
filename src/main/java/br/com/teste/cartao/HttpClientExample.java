package br.com.teste.cartao;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientExample {

    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) throws Exception {

        HttpClientExample obj = new HttpClientExample();

        try {
            //System.out.println("Testing 1 - Send Http GET request");
            //obj.sendGet();

            //System.out.println("Testing 2 - Send Http POST request");
            obj.sendPost();
        } finally {
            obj.close();
        }
    }

    private void close() throws IOException {
        httpClient.close();
    }

    private void sendGet() throws Exception {

        HttpGet request = new HttpGet("https://www.google.com/search?q=mkyong");

        // add request headers
        request.addHeader("custom-key", "mkyong");
        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        }

    }

    private void sendPost() throws Exception {

        HttpPost post = new HttpPost("https://mqlinuxext.serasa.com.br/Homologa/consultahttps");
    	//String p = "71645060AdvxH19@        B49C      000000000000272JC     FI                   S99SINIAN                               N                                                                                                                                                                                                                                                                                                                  P002RSPU                                                                                                           I00100RS                                                                                                           T999";
    	String p = "71645060AdvxH19@        B49C      000000000000272FC     FI                   S99SINIAN                               N                                                                                                                                                                                                                                                                                                                  P002RSPU                                                                                                           I00100R                                                                                                            T999";
        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("p", p));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(">>>"+EntityUtils.toString(response.getEntity())+"<<<");
        }

    }

}