import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ExchangeGenerate exchange_generate = new ExchangeGenerate();
        String json = exchange_generate.buscarMoneda("USD");

        JsonElement jsonElement = JsonParser.parseString(json);

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        System.out.println(jsonObject.getAsJsonObject("conversion_rates").get("AFN"));
    }
}


