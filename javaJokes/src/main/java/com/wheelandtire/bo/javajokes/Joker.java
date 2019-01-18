package com.wheelandtire.bo.javajokes;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.JSONObject;

public class Joker {

    private static final String CHUCKNORRIS_IO_API_URL = "https://api.chucknorris.io/jokes/random";
    private static final String VALUE = "value";
    private static final String DEFAULT_JOKE = "Chuck Norris doesn't fly, gravity collapses around him.";

    public static String getJoke() {
        try {
            final Client client = Client.create();
            final WebResource webResource = client.resource(CHUCKNORRIS_IO_API_URL);
            final ClientResponse response = webResource.get(ClientResponse.class);

            if (response.getStatus() != 200) {
                return DEFAULT_JOKE;
            }

            final String result = response.getEntity(String.class);
            final JSONObject json = new JSONObject(result);

            return json.getString(VALUE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}