import java.util.Map;

/**
 * Classe que define a estrutura de dados (Record) para a resposta da ExchangeRate-API.
 */
public class Api {

    // Record para facilitar a manipulação dos dados JSON
    public record ExchangeRateApi(String result, String base_code, Map<String, Double> conversion_rates) {

        @Override
        public String toString() {
            return  "Código da Moeda Base: " + base_code +
                    "\n Conversões de Valores (selecionadas): " + conversion_rates;
        }

        public Map<String, Double> conversionRates() {
            return conversion_rates;
        }
    }
}