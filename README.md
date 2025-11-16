🏆 Desafio de Conversor de Moedas (Alura)Este projeto foi desenvolvido como parte de um Desafio de Programação da Alura, com o objetivo de consolidar conhecimentos em consumo de API, manipulação de JSON, estrutura de classes (POO) e tratamento de exceções em Java.✨ FuncionalidadesTaxas em Tempo Real: Obtém as taxas de câmbio atualizadas utilizando o HttpClient do Java para consultar a API externa.Menu Interativo (CLI): Permite que o usuário escolha entre conversões pré-definidas (BRL para USD, EUR, GBP, JPY, CHF) ou realize conversões personalizadas entre qualquer par de moedas.Design Orientado a Objetos (POO): Utiliza Records (Java 16+) para tipagem forte e imutável dos dados da API, separando claramente as responsabilidades: UI (Conversor), Consumo de API (BuscaValor) e Estrutura de Dados (Api).Tratamento de Erros: Inclui tratamento para erros de conexão e falhas na resposta da API, garantindo robustez à aplicação.🛠️ Tecnologias e DependênciasO projeto é construído em Java e requer uma dependência externa para o parsing de JSON.Linguagem: Java (Requer JDK 11 ou superior para usar o HttpClient nativo).Manipulação de JSON: Biblioteca Google Gson (Para converter a resposta JSON da API em objetos Java).API de Câmbio: ExchangeRate-API.⚙️ Como Configurar e Rodar1. Pré-requisitosCertifique-se de ter o Java Development Kit (JDK) 11+ instalado.2. Estrutura do Projeto e DependênciaPara compilar e rodar, você deve ter a biblioteca Gson no seu classpath.Baixe o Gson: Obtenha o arquivo JAR do Gson (ex: gson-2.10.1.jar).Organização: Crie a seguinte estrutura e coloque o JAR do Gson na pasta lib/:.
├── Api.java
├── BuscaValor.java
├── Conversor.java
└── lib/
└── gson-2.10.1.jar
3. ExecuçãoExecute os comandos no terminal, no diretório raiz do projeto:📌 CompilaçãoCompile todos os arquivos Java, incluindo a biblioteca Gson no classpath:Bash# Para Linux/macOS
   javac -cp "lib/*:." *.java

# Para Windows (use ponto e vírgula)
javac -cp "lib/*;." *.java
📌 ExecuçãoExecute a classe principal (Conversor), garantindo que o Gson continue no classpath:Bash# Para Linux/macOS
java -cp "lib/*:." Conversor

# Para Windows
java -cp "lib/*;." Conversor
📁 Detalhes da ImplementaçãoArquivoConceito PrincipalDescriçãoConversor.javaFluxo de Aplicação (Main)Contém o main e o Scanner para a interação do usuário. É o ponto de partida do sistema.BuscaValor.javaConsumo de API e ParsingImplementa a interface com a web, usando HttpClient e Gson. Lida com a serialização JSON e tratamento de exceções de rede.Api.javaData Transfer Object (DTO) e RecordDefine o public record ExchangeRateApi(...), garantindo que os dados recebidos da API sejam imutáveis, estruturados e fáceis de usar.🚀 Desafio Concluído!Este projeto demonstra a capacidade de integrar diferentes módulos do Java, consumir serviços web RESTful e aplicar boas práticas de POO e tratamento de exceções.🙋 ContribuiçõesEste é um projeto de estudo, mas sugestões de melhoria (como testes unitários ou uso de bibliotecas como Maven/Gradle) são sempre bem-vindas!
