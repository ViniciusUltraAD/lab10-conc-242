package com.example.concorrente_lab10.controller;

import com.example.concorrente_lab10.models.Dto.*;
import com.example.concorrente_lab10.service.produtoInterface.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST responsável por gerenciar operações relacionadas a produtos,
 * como listagem, cadastro, compra, atualização de estoque e geração de relatórios de vendas.
 */
@RestController
@AllArgsConstructor
public class ProdutoController {

    /**
     * Serviço responsável pelas operações de negócio envolvendo produtos.
     */
    private ProdutoService produtoService;

    /**
     * Lista todos os produtos disponíveis.
     *
     * @return ResponseEntity contendo uma lista de {@link ProdutoGetDto} e o statuxs HTTP 200.
     */
    @GetMapping("/products")
    @Operation(summary = "Lista Todos os Produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos Retornados com Sucesso")
    })
    public ResponseEntity<List<ProdutoGetDto>> getTodosProdutos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoService.getTodosProdutos());
    }

    /**
     * Retorna um produto específico a partir de seu ID.
     *
     * @param id ID do produto a ser recuperado.
     * @return ResponseEntity contendo o {@link ProdutoGetDto} e o status HTTP 200 se encontrado,
     * ou 404 se não encontrado.
     */
    @GetMapping("/products/{id}")
    @Operation(summary = "Lista o produto recebido como paramêtro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto Retornado com Sucesso."),
            @ApiResponse(responseCode = "404", description = "Produto Não Encontrado.")
    })
    public ResponseEntity<ProdutoGetDto> getProduto(@PathVariable String id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoService.getProduto(id));
    }

    /**
     * Cadastra um novo produto.
     *
     * @param produtoPostDto Dados do produto a ser cadastrado.
     * @return ResponseEntity contendo o {@link ProdutoResponseCadastroDto} e o status HTTP 201 se criado,
     * ou 409 se o produto já estiver cadastrado.
     */
    @PostMapping("/products")
    @Operation(summary = "Cadastra Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto Cadastrado com Sucesso."),
            @ApiResponse(responseCode = "409", description = "Produto Já cadastrado com esse ID.")
    })
    public ResponseEntity<ProdutoResponseCadastroDto> cadastraProduto(
            @RequestBody @Valid ProdutoPostDto produtoPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoService.cadastraProduto(produtoPostDto));
    }

    /**
     * Realiza a compra de um produto, verificando o estoque disponível.
     *
     * @param produtoCompraDto Dados da compra do produto.
     * @return contendo o {@link ProdutoResponseCompraDto} e o status HTTP 200 se comprado,
     * 400 se o estoque for insuficiente, ou 404 se o produto não for encontrado.
     */
    @PostMapping("/purchase")
    @Operation(summary = "Compra Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto comprado com Sucesso."),
            @ApiResponse(responseCode = "400", description = "Estoque insuficiente."),
            @ApiResponse(responseCode = "404", description = "Produto Não Encontrado.")
    })
    public ResponseEntity<ProdutoResponseCompraDto> compraProduto(
            @RequestBody @Valid ProdutoCompraDto produtoCompraDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoService.compraProduto(produtoCompraDto));
    }

    /**
     * Atualiza o estoque de um produto específico.
     *
     * @param id ID do produto a ter o estoque atualizado.
     * @param produtoPutDto Dados atualizados de estoque.
     * @return ResponseEntity contendo o {@link ProdutoResponseUpdateEstoqueDto} e o status HTTP 200 se atualizado,
     * ou 404 se o produto não for encontrado.
     */
    @PutMapping("/products/{id}/stock")
    @Operation(summary = "Atualiza estoque de Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque atualizado."),
            @ApiResponse(responseCode = "404", description = "Produto Não Encontrado.")
    })
    public ResponseEntity<ProdutoResponseUpdateEstoqueDto> atualizaEstoque(
            @PathVariable String id,
            @RequestBody @Valid ProdutoPutDto produtoPutDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoService.atualizaEstoque(id, produtoPutDto));
    }

    /**
     * Gera um relatório de vendas com os dados das compras realizadas.
     *
     * @return ResponseEntity contendo o {@link RelatorioVendasResponseDto} e o status HTTP 200.
     */
    @GetMapping("/sales/report")
    @Operation(summary = "Gera o Relatorio das compras de Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relatorio Gerado com Sucesso."),
    })
    public ResponseEntity<RelatorioVendasResponseDto> geraRelatorio() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoService.geraRelatorio());
    }
}
