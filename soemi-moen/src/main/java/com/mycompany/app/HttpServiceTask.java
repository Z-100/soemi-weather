package com.mycompany.app;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpServiceTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Get input parameter from process variable
        String inputParameter = (String) execution.getVariable("inputParameter");

        // Make HTTP request
        String apiUrl = "https://api.example.com/endpoint?param=" + inputParameter;
        String httpResponse = sendHttpRequest(apiUrl);

        // Set the output parameter as a process variable
        execution.setVariable("outputParameter", httpResponse);
    }

    private String sendHttpRequest(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            // Set up HTTP GET request
            connection.setRequestMethod("GET");

            // Read the response
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } finally {
            connection.disconnect();
        }
    }
}
