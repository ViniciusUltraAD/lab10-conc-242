package com.example.concorrente_lab10.controller;

import com.example.concorrente_lab10.models.Dto.*;
import com.example.concorrente_lab10.service.produtoInterface.ProdutoService;
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
    public ResponseEntity<List<ProdutoGetDto>> getTodosProdutos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoService.getTodosProdutos());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProdutoGetDto> getProduto(@PathVariable String id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoService.getProduto(id));
    }

    @PostMapping("/products")
    public ResponseEntity<ProdutoResponseCadastroDto> cadastraProduto(
            @RequestBody @Valid ProdutoPostDto produtoPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoService.cadastraProduto(produtoPostDto));
    }

    @PostMapping("/purchase")
    public ResponseEntity<ProdutoResponseCompraDto> compraProduto(
            @RequestBody @Valid ProdutoCompraDto produtoCompraDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoService.compraProduto(produtoCompraDto));
    }

    @PutMapping("/products/{id}/stock")
    public ResponseEntity<ProdutoResponseUpdateEstoqueDto> put(
            @PathVariable String id,
            @RequestBody ProdutoPutDto produtoPutDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoService.atualizaEstoque(id, produtoPutDto));
    }

    @GetMapping("/sales/report")
    public ResponseEntity<ProdutoResponseRelatorioDto> geraRelatorio() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoService.geraRelatorio());
    }
}
