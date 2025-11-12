/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ITN_SYNC_SO.ws;

import ITN_SYNC_SO.db.DbProperties;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import org.apache.log4j.Logger;

/**
 *
 * @author lucio
 */
public class WebServiceHandler {
    
    private final Logger logger = Logger.getLogger(WebServiceHandler.class);
    private final String endpoint;
    private final String apiKey;
    private final String apiHeader; //"X-Gravitee-Api-Key";
    
    private final DbProperties dbProperties;

    public WebServiceHandler(DbProperties dbProperties) {
        this.dbProperties = dbProperties;
        endpoint = dbProperties.getWsApiEndpoint();
        apiKey = dbProperties.getWsApiKey();
        apiHeader = dbProperties.getWsApiKeyHeader(); //"X-Gravitee-Api-Key";
    }
    
    
    public String execRequestAndGetBody(String request) {
        String response = null;
        try {
            HttpClient client = getHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder(new URI(endpoint)).POST(HttpRequest.BodyPublishers.ofString(request))
                    .header(apiHeader, apiKey).build();
            
            logger.debug("Sendig request to " + endpoint + ": \n" + request);
            
            HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() == 200) {
                response = httpResponse.body();

                logger.debug("Response: " + httpResponse.body());
            }
        } catch (KeyManagementException | NoSuchAlgorithmException | IOException | URISyntaxException | InterruptedException e) {
            logger.error("Error in getResponse", e);
        }

        return response;
    }

    private HttpClient getHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        HttpClient client = null;
        if (endpoint.startsWith("https")) {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{getDummyTrustManager()}, new SecureRandom());
            client = HttpClient.newBuilder().sslContext(sslContext).build();
        } else {
            client = HttpClient.newBuilder().build();
        }

        return client;
    }

    /*
    
    <SetInterventionResponse>
        <responseStatus></responseStatus>   => 0 = OK, 1 = Internal Error, 2 = Operazione Fallita
        <responseMessage></responseMessage> messaggio di errore
    </SetInterventionResponse>
    */
    
    private TrustManager getDummyTrustManager() {
        return new X509ExtendedTrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) throws java.security.cert.CertificateException {
                
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) throws java.security.cert.CertificateException {
                
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws java.security.cert.CertificateException {
                
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws java.security.cert.CertificateException {
                
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
                
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
                
            }

        };
    }

}
