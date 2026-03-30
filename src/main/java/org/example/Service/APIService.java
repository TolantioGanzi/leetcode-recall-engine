package org.example.Service;


import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

// Send and Receive request to API
public class APIService {
    private final OkHttpClient httpClient;
    private final String requestUrl;
    public APIService(OkHttpClient httpClient, String requestUrl) {
        this.httpClient = httpClient;
        this.requestUrl = requestUrl;
    }

    // Send *PREPARED request
    public String sendRequest(Request request) throws IOException {
        try {
            Response response = httpClient.newCall(request).execute();
            if(!response.isSuccessful()) {
                throw new RuntimeException("Request failed " + request);
            }
            return response.body().string();
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    // Build HTTP Request
    public void buildRequest(String problemName) {
        // The parser should clean the input and make it call-ready for the API
        HttpUrl.Builder urlBuilder = HttpUrl.parse(requestUrl + "/select?titleSlug=" + problemName).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
    }
}
