package com.example.concorrente_lab10;

import com.example.concorrente_lab10.models.Dto.ProdutoCompraDto;
import com.example.concorrente_lab10.models.Dto.ProdutoPostDto;
import com.example.concorrente_lab10.models.Dto.ProdutoPutDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GeradorRequisicao {

    private static final int NUM_THREADS = 50;
    private static final int NUM_REQUESTS_PER_THREAD = 20;
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Random random = new Random();
    private static final List<ProdutoCompraDto> produtosCompraDto = new ArrayList<>();
    private static final List<ProdutoPutDto> produtosPutDto = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException, IOException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        setup();

        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < NUM_REQUESTS_PER_THREAD; j++) {
                    acaoThread();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
    }

    private static void acaoThread() {
        int action = random.nextInt(4);

        try {
            switch (action) {
                case 0 -> simulaListaProdutos();
                case 1 -> simulaCompra();
                case 2 -> simulaAtualizarEstoque();
                case 3 -> simulaGerarRelatorio();
            }
        } catch (Exception e) {
            System.err.println("Erro ao executar ação: " + e.getMessage());
        }
    }

    private static void simulaListaProdutos() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/products"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("[Consulta Produtos] Status: " + response.statusCode());
    }

    private static void simulaCompra() throws Exception {
        int indice = random.nextInt(30);
        ProdutoCompraDto produtoCompraDto = produtosCompraDto.get(indice);
        String json = objectMapper.writeValueAsString(produtoCompraDto);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/purchase"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("[Compra Produto] Status: " + response.statusCode());
    }

    private static void simulaAtualizarEstoque() throws Exception {
        int indice = random.nextInt(30);
        String prodctId = String.valueOf(indice%10);
        ProdutoCompraDto produtoCompraDto = produtosCompraDto.get(indice);
        String json = objectMapper.writeValueAsString(produtoCompraDto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/products/" + prodctId + "/stock"))
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("[Atualiza Estoque] Status: " + response.statusCode());
    }

    private static void simulaGerarRelatorio() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sales/report"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("[Geração de Relatório] Status: " + response.statusCode());
    }

    private static void setup() throws IOException, InterruptedException {
        List<ProdutoPostDto> produtos = List.of(
                new ProdutoPostDto("1", "Teclado Aula-F75", 240.00, 100),
                new ProdutoPostDto("2", "Camisa São Paulo FC 2024", 199.90, 50),
                new ProdutoPostDto("3", "Headset Gamer Redragon Zeus X", 349.99, 80),
                new ProdutoPostDto("4", "Controle DualSense PS5", 429.90, 60),
                new ProdutoPostDto("5", "Elden Ring", 299.00, 40),
                new ProdutoPostDto("6", "Mouse Logitech G203 RGB", 159.99, 100),
                new ProdutoPostDto("7", "Notebook Gamer Acer Nitro 5", 4899.00, 20),
                new ProdutoPostDto("8", "Camiseta Overwatch 2 Oficial", 89.90, 70),
                new ProdutoPostDto("9", "The Forest", 5.00, 300),
                new ProdutoPostDto("10", "Cadeira Gamer", 740.00, 15)
        );
        for (ProdutoPostDto produtoPostDto : produtos) {
            String json = objectMapper.writeValueAsString(produtoPostDto);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/products"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        }

        for (int i = 0; i < 30; i++) {
            String id = produtos.get(i%10).getId();
            int quantidadeCompra = random.nextInt(20);
            int quatidadeEstoque = random.nextInt(100);
            produtosCompraDto.add(new ProdutoCompraDto(id, quantidadeCompra));
            produtosPutDto.add(new ProdutoPutDto(quatidadeEstoque));
        }
    }
}
