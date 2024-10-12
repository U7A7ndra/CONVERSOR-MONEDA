
public class ConversorMoneda {
    private final API api;

    public ConversorMoneda(API api) {
        this.api = api;
    }

    public double convertir(double valor, String monedaOrigen, String monedaDestino) {
        double tasaCambio = api.obtenerTasaCambio(monedaOrigen, monedaDestino);
        return valor * tasaCambio;
    }
}
