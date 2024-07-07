

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeGenerate {
    public String buscarMoneda(String moneda){
        String api_key="4bacca3f34c6e31dc786ed04";
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/"+api_key+"/latest/"+moneda);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException("No encontré esa película.");
        }

    }
}
