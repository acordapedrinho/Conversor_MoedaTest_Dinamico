# 🏆 Desafio de Conversor de Moedas em Java (Alura)

Este projeto foi desenvolvido como parte de um **Desafio de Programação da Alura**, com o objetivo de consolidar conhecimentos em: consumo de API, manipulação de JSON, estrutura de classes (POO) e tratamento de exceções em Java.

---

## ✨ Funcionalidades

* **Taxas em Tempo Real:** Obtém as taxas de câmbio atualizadas utilizando o `HttpClient` nativo do Java.
* **Menu Interativo (CLI):** Oferece opções para conversões populares (Ex: BRL -> USD) e conversões personalizadas entre qualquer par de moedas.
* **Design POO:** Utiliza o recurso **Records (Java 16+)** para criar DTOs (Data Transfer Objects) imutáveis e claros.
* **Robustez:** Implementa tratamento de exceções para erros de rede (`IOException`, `InterruptedException`) e falhas na resposta da API.

---

## 🛠️ Tecnologias e Dependências

| Componente | Detalhe |
| :--- | :--- |
| **Linguagem** | Java (Requer **JDK 11 ou superior**). |
| **JSON** | Biblioteca **Google Gson** (Para converter JSON em objetos Java). |
| **API de Câmbio** | [ExchangeRate-API](https://www.exchangerate-api.com/). |

---

## ⚙️ Como Configurar e Rodar

### 1. Pré-requisitos

Você precisará do **Java Development Kit (JDK) 11+** e do arquivo JAR da biblioteca **Gson**.
precisará ter uma  chave da API ExchangeRate-API e adicioná-la , na classe "BuscarValor" na linha 20.

### 2. Estrutura do Projeto

Crie a seguinte estrutura de diretórios e inclua o arquivo JAR do Gson na pasta `lib/`:

. ├── Api.java ├── BuscaValor.java ├── Conversor.java └── lib/ └── gson-2.10.1.jar # (Ou a versão mais recente)


### 3. Execução

Use os comandos abaixo no terminal, no diretório raiz do projeto, para compilar e executar, garantindo que o Gson esteja no *classpath*.

#### Compilação


## Para Linux/macOS
javac -cp "lib/*:." *.java 

## Para Windows (use ponto e vírgula)
javac -cp "lib/*;." *.java

##Arquivo,Responsabilidade
Conversor.java,"Camada de UI: Contém o método main(), o menu interativo e a lógica de leitura da entrada do usuário."
BuscaValor.java,"Camada de Serviço: Implementa a requisição HTTP (HttpClient) e o parsing do JSON (Gson), isolando a lógica de comunicação com a API."
Api.java,Camada de Dados: Define o record Api.ExchangeRateApi para mapear e tipar a resposta da API de forma imutável.


#💰 Conversor de Moedas em Java



## Demonstração de Uso

### Conversão Simples (BRL para USD)
Aqui está a tela mostrando a conversão de 1000 BRL para USD (Opção 1):
![Captura de tela da conversão de 1000 BRL para USD]<img width="589" height="622" alt="image" src="https://github.com/user-attachments/assets/e53aac88-8cd3-42a5-a531-aaad5c6edce8" />
)




---






## Conversão Personalizada (USD para CHF)
É possível realizar conversões entre quaisquer duas moedas usando a opção 6 (Conversão Personalizada):

<img width="625" height="821" alt="image" src="https://github.com/user-attachments/assets/e46bd87e-837b-4738-907d-07775790bb55" />

