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

@RestController
@AllArgsConstructor
public class ProdutoController {

    private ProdutoService produtoService;

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

    @GetMapping("/sales/report")
    @Operation(summary = "Gera o Relatorio das compras de Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relatorio Gerado com Sucesso."),
    })
    public ResponseEntity<ProdutoResponseRelatorioDto> geraRelatorio() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoService.geraRelatorio());
    }
}
