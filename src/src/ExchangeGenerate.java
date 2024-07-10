import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeGenerate {
    private static final String API_KEY = "4bacca3f34c6e31dc786ed04";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    private final HttpClient client = HttpClient.newHttpClient();

    public String buscarMoneda(String moneda) {
        URI direccion = URI.create(BASE_URL + API_KEY + "/latest/" + moneda);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los datos de la API.");
        }
    }

    public double obtenerTasaDeCambio(String monedaDesde, String monedaHacia) {
        try {
            String json = buscarMoneda(monedaDesde.toUpperCase());
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

            JsonObject tasasDeCambio = jsonObject.getAsJsonObject("conversion_rates");
            return tasasDeCambio.get(monedaHacia.toUpperCase()).getAsDouble();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las tasas de cambio.");
        }
    }

    public double convertirMoneda(double monto, String monedaDesde, String monedaHacia) {
        try {
            double tasaDeCambio = obtenerTasaDeCambio(monedaDesde, monedaHacia);
            return monto * tasaDeCambio;
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir moneda: " + e.getMessage());
        }
    }

    public String[] obtenerMonedasDisponibles() {
        String json = buscarMoneda("USD");
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

        JsonObject tasasDeCambio = jsonObject.getAsJsonObject("conversion_rates");
        return tasasDeCambio.keySet().toArray(new String[0]);
    }
}
