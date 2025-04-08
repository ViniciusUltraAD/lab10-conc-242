# lab10-conc-242

Esse repositório contém um pequeno servidor REST concorrente para uma loja online, sendo o Lab 10 da disciplina de "Programação Concorrente" do curso de Ciência da Computação do período 24.2

## Alunos
- Arthur Gabriel Santos Albuquerque
- Diego Souto Maior Aleixo
- Victor Vili Xavier Luna
- Vinicius Ataide Delgado

## Estrutura do Repositório
```plaintext
├── mvnw                                          # Script para executar o Maven Wrapper em sistemas Unix/Linux
├── mvnw.cmd                                      # Script para executar o Maven Wrapper em sistemas Windows
├── pom.xml                                       # Arquivo de configuração do Maven que define as dependências e plugins do projeto
├── README.md                                     # Documentação do repositório
└── src                                           # Diretório fonte do repositório contendo o código-fonte, recursos do projeto e a aba de testes
    ├── main                                      # Código-fonte do projeto
    │   ├── java/com/example/concorrente_lab10    # Pacote base do aplicativo
    │   └── resources                             # Diretório para arquivos de recursos
    └── test                                      # Diretório para testes
```

## Como rodar o projeto

O padrão para rodar os testes é usar de forma manual um frontend com swagger que explicita as funcionalidades REST do sistema

Utilize o link a seguir para acessar o swagger: [Swagger](http://localhost:8080/swagger-ui/index.html#/)

## Como rodar os testes

Rode `mvn test` na raiz do projeto