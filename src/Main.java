mport java.util.Scanner;
import java.net.http.HttpClient;
import java.util.Scanner;

/**
 * Classe principal que contém o menu interativo e a lógica de conversão de moedas.
 */
public class Conversor {
    public static void main(String[] args) {

        BuscarValor novaConsulta = new BuscarValor();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo(a) ao Conversor de Moedas!");
        System.out.println("As taxas de câmbio serão obtidas em tempo real.");

        while (true) {
            System.out.println("\n***** CONVERSOR DE MOEDAS *****");

            System.out.println("1 - BRL --> Dólar Americano (USD)");
            System.out.println("2 - BRL --> Euro (EUR)");
            System.out.println("3 - BRL --> Libra Esterlina (GBP)");
            System.out.println("4 - BRL --> Iene Japonês (JPY)");
            System.out.println("5 - BRL --> Franco Suíço (CHF)");
            System.out.println("7 - Conversão Personalizada (Ex: USD para EUR)");
            System.out.println("6 - Sair");
            System.out.println("De acordo com o menu acima, escolha uma opção:");

            // Leitura segura do inteiro
            int escolha;
            if (scanner.hasNextInt()) {
                escolha = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
                continue;
            }

            switch (escolha) {
                case 1:
                    realizarConversao(novaConsulta, "BRL", "USD", scanner);
                    break;
                case 2:
                    realizarConversao(novaConsulta, "BRL", "EUR", scanner);
                    break;
                case 3:
                    realizarConversao(novaConsulta, "BRL", "GBP", scanner);
                    break;
                case 4:
                    realizarConversao(novaConsulta, "BRL", "JPY", scanner);
                    break;
                case 5:
                    realizarConversao(novaConsulta, "BRL", "CHF", scanner);
                    break;
                case 7:
                    realizarConversaoPersonalizada(novaConsulta, scanner);
                    break;
                case 6:
                    System.out.println("Saindo do programa...");
                    // Fecha o scanner ao sair
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    /**
     * Realiza a conversão entre a moeda base e a moeda de destino.
     *
     * @param consulta     A instância de BuscarValor.
     * @param moedaBase    O código da moeda base (aquela que será convertida).
     * @param moedaDestino O código da moeda de destino.
     * @param scanner      O scanner para ler a entrada do usuário.
     */
    private static void realizarConversao(BuscarValor consulta, String moedaBase, String moedaDestino, Scanner scanner) {
        Api.ExchangeRateApi consultaMoeda;

        try {
            // Faz a consulta, usando a moedaBase definida
            consultaMoeda = consulta.consultarValorMoeda(moedaBase);
        } catch (RuntimeException e) {
            System.out.println("ERRO ao consultar taxas: " + e.getMessage());
            return;
        }

        if (consultaMoeda != null && consultaMoeda.conversionRates() != null) {
            // Obtém a taxa de câmbio específica
            Double taxaDeCambio = consultaMoeda.conversionRates().get(moedaDestino);

            if (taxaDeCambio != null) {
                double valorEmMoedaBase;

                System.out.printf("Taxa atual: 1 %s = %.4f %s%n", moedaBase, taxaDeCambio, moedaDestino);
                System.out.printf("Digite o valor em %s que você deseja converter:%n", moedaBase);

                // Leitura e validação da entrada de valor
                if (scanner.hasNextDouble()) {
                    valorEmMoedaBase = scanner.nextDouble();
                    scanner.nextLine(); // Limpar o buffer
                    double valorConvertido = valorEmMoedaBase * taxaDeCambio;

                    // Saída formatada
                    System.out.printf("O valor de %.2f %s convertido para %s é de: %.2f%n",
                            valorEmMoedaBase, moedaBase, moedaDestino, valorConvertido);
                    System.out.println("\n********************************");
                } else {
                    System.out.println("Valor inválido digitado. Por favor, use números.");
                    scanner.nextLine();
                }
            } else {
                System.out.println("Taxa de câmbio para " + moedaDestino + " não disponível. Verifique se o código da moeda de destino é válido.");
                System.out.println("\n********************************");
            }
        } else {
            System.out.println("Erro interno ao processar as taxas de câmbio.");
            System.out.println("\n********************************");
        }
    }

    /**
     * Lida com a entrada de dados do usuário para uma conversão de moeda arbitrária.
     *
     * @param consulta A instância de BuscarValor.
     * @param scanner  O scanner para ler a entrada do usuário.
     */
    private static void realizarConversaoPersonalizada(BuscarValor consulta, Scanner scanner) {
        System.out.println("\n--- Conversão Personalizada ---");
        System.out.println("Insira o código da MOEDA BASE (código de 3 letras, Ex: USD, EUR, BRL):");
        String moedaBase = scanner.nextLine().toUpperCase();

        System.out.println("Insira o código da MOEDA DE DESTINO (código de 3 letras, Ex: JPY, GBP, CAD):");
        String moedaDestino = scanner.nextLine().toUpperCase();

        // Reutiliza a função de conversão principal com as moedas fornecidas pelo usuário
        realizarConversao(consulta, moedaBase, moedaDestino, scanner);
    }
}