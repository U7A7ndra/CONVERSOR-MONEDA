
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

    public class API {
        private final String apiKey = "3de33773e7f91e27f931fafc";

        public double obtenerTasaCambio(String monedaOrigen, String monedaDestino) {
            try {
                String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + monedaOrigen;
                URL url = new URL(urlStr);
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.connect();

                int responseCode = request.getResponseCode();
                if (responseCode != 200) {
                    throw new RuntimeException("HTTP Response Code: " + responseCode);
                }

                BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) request.getContent()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();

                String json = content.toString();
                return extraerTasaCambio(json, monedaDestino);

            } catch (Exception e) {
                System.out.println("Error al obtener la tasa de cambio: " + e.getMessage());
                return 0;
            }
        }

        private double extraerTasaCambio(String json, String monedaDestino) {
            String searchString = "\"" + monedaDestino + "\":";
            int index = json.indexOf(searchString);
            if (index == -1) {
                throw new RuntimeException("Tasa de cambio no encontrada para la moneda: " + monedaDestino);
            }

            int startIndex = index + searchString.length();
            int endIndex = json.indexOf(",", startIndex);

            if (endIndex == -1) {
                endIndex = json.indexOf("}", startIndex);
            }

            String tasaCambioString = json.substring(startIndex, endIndex).trim();
            return Double.parseDouble(tasaCambioString);
        }
    }
