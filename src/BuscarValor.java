
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

    /**
     * Classe responsável por buscar os valores de cotação na API ExchangeRate.
     * **É necessário ter a biblioteca Gson no classpath do projeto.**
     */
    public class BuscarValor {
        // Sua chave de API. Por segurança, em projetos reais, não a deixe codificada.
        private static final String API_KEY = "Coloque sua chave aqui";

        /**
         * Consulta o valor das moedas usando o código da moeda base fornecido.
         * @param codigoMoedaBase O código da moeda base (ex: "BRL", "USD").
         * @return Um objeto ExchangeRateApi contendo as taxas de conversão.
         * @throws RuntimeException Se houver erro de conexão ou na resposta da API.
         */
        public Api.ExchangeRateApi consultarValorMoeda(String codigoMoedaBase) throws RuntimeException {

            // Monta a URL para obter as taxas mais recentes baseadas na moedaBase
            String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", API_KEY, codigoMoedaBase);
            URI valorConsultado = URI.create(url);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(valorConsultado)
                    .build();

            try {
                // Envia a requisição e recebe a resposta como String
                HttpResponse<String>response = HttpClient
                        .newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    // Se a API retornar um erro HTTP (como 404, 403)
                    throw new RuntimeException("Erro na API: Status Code " + response.statusCode());
                }

                return parseExchangeRate(response.body());

            }catch (java.io.IOException | InterruptedException e){
                // Tratamento de erros de conexão ou interrupção (Timeouts, rede, etc.)
                throw new RuntimeException("Erro de conexão ao buscar APi: " + e.getMessage());

            }catch (Exception e) {
                // Erro genérico, incluindo problemas no JSON Parsing
                throw new RuntimeException("Erro desconhecido ao processar dados: Verifique se Escreveu Corretamente = " + e.getMessage());
            }
        }

        // Método auxiliar para analisar a string JSON
        private Api.ExchangeRateApi parseExchangeRate(String json) {
            // Usa JsonParser para analisar a String JSON
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            String result = jsonObject.get("result").getAsString();
            String baseCode = jsonObject.get("base_code").getAsString();

            if (!"success".equalsIgnoreCase(result)){
                // Se a API retornar falha (ex: código de moeda inválido)
                throw new RuntimeException("Falha na resposta da APi. Verifique o código da moeda base: " + result);
            }

            // Obtém o objeto JSON que contém todas as taxas de conversão
            JsonObject taxaDeConversaoObjeto = jsonObject.getAsJsonObject("conversion_rates");

            // O filtro garante que a moeda base e as moedas alvo estejam no mapa de taxas.
            Map<String,Double> filtraTaxas =  filtrarTaxas(taxaDeConversaoObjeto, baseCode);

            return  new Api.ExchangeRateApi(result, baseCode, filtraTaxas);
        }

        // Método auxiliar para filtrar apenas as moedas desejadas, AGORA ACEITANDO A MOEDA BASE
        private Map<String, Double> filtrarTaxas(JsonObject taxaDeConversaoObjeto, String moedaBase) {
            Map<String, Double> filtrandoTaxas = new HashMap<>();

            // Moedas padrão + a moeda base atual (garantindo que ela sempre seja inclusa)
            Set<String> moedasAlvo = new HashSet<>(Arrays.asList("USD", "EUR", "GBP", "BRL", "JPY", "CHF"));
            moedasAlvo.add(moedaBase);

            for (String moeda : moedasAlvo) {
                if (taxaDeConversaoObjeto.has(moeda)) {
                    filtrandoTaxas.put(moeda, taxaDeConversaoObjeto.get(moeda).getAsDouble());
                }
            }
            return filtrandoTaxas;
        }
    }

























