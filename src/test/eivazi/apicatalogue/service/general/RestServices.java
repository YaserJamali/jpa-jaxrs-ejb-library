/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.tamin.infra.apicatalogue.service.general;


import ir.tamin.framework.cdi.util.WebProperties;
import ir.tamin.framework.core.util.Bundle;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.inject.Inject;
import javax.net.ssl.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author L_Eivazi
 */
public class RestServices {

    @Inject
    @WebProperties
    Bundle webProperties;
    private Client client;

    @Inject
    @WebProperties
    private Bundle webBundle;

    public void initClient() {
        ClientConfig cc = new ClientConfig();
        cc.property(ClientProperties.CONNECT_TIMEOUT, 5000);
        cc.property(ClientProperties.READ_TIMEOUT, 15000);
        cc.property(ClientProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
        cc.property(ClientProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);

        client = ClientBuilder.newClient(cc);
        //client.register(JacksonJsonProvider.class);
        client.register(JacksonFeature.class);
    }



    public void closeClient() {
        client.close();
    }


    public String getResource(String property) {
        String resource = "";
        resource = webProperties.getProperty(property);
        return resource;
    }

    public String postRequest(String url, Object object, String token) {
        initClient();
        boolean result = false;
        WebTarget webResource = client.target(url);
        Response response = null;
        try {
            response = webResource
                    .request(MediaType.APPLICATION_JSON)
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .header("Authorization", String.format("Bearer %s", token))
                    .post(Entity.entity(object, MediaType.APPLICATION_JSON));
            String str = response.readEntity(String.class);

            Logger.getLogger(RestServices.class.getName()).log(Level.INFO, "post response is:" + str + " url is :" + url);
            if (response.getStatus() == Response.Status.CREATED.getStatusCode()
                    || response.getStatus() == Response.Status.OK.getStatusCode()) {
                return null;
            } else {
                return str;
            }
        } finally {
            if (response != null) {
                response.close();
            }

            closeClient();
        }
    }
    public String getRequest(String url, String token) {
        initClient();
        String output = null;
        WebTarget webResource = client.target(url);
        Response response = null;
        try {
            response = webResource
                    .request(MediaType.APPLICATION_JSON)
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + token)
                    .get();
            output = response.readEntity(String.class);
            Logger.getLogger(RestServices.class.getName()).log(Level.INFO, url + " " + output);
            if (!(response.getStatus() == 200 || response.getStatus() == 204)) {
                System.out.println("Failed with content: " + output);
                Logger.getLogger(RestServices.class.getName()).log(Level.SEVERE, url + " " + output);

            }

            return output;
        } finally {
            if (response != null) {
                response.close();
            }

            closeClient();
        }
    }
}

