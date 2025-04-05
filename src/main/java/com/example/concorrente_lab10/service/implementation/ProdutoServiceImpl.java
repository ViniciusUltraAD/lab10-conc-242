package com.example.concorrente_lab10.service.implementation;

import com.example.concorrente_lab10.models.Dto.*;
import com.example.concorrente_lab10.models.Produto;
import com.example.concorrente_lab10.repository.ProdutoRepository;
import com.example.concorrente_lab10.service.produtoInterface.ProdutoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<ProdutoGetDto> getTodosProdutos() {
        return this.produtoRepository.getTodosProdutos().stream().map(ProdutoGetDto::new).toList();
    }

    @Override
    public ProdutoGetDto getProduto(String id) {
        Produto produto = this.getProdutoEntity(id);
        return new ProdutoGetDto(produto);
    }

    @Override
    @Transactional
    public ProdutoResponseCompraDto compraProduto(ProdutoCompraDto produtoCompraDto) {
        Produto produto = this.getProdutoEntity(produtoCompraDto.getId());
        if (produto.getQuantity().get() < produtoCompraDto.getQuantity()) {
            throw new IllegalArgumentException();
        }
        produto.reduzirEstoque(produtoCompraDto.getQuantity());
        return new ProdutoResponseCompraDto(produto);
    }

    @Override
    public ProdutoResponseCadastroDto cadastraProduto(ProdutoPostDto produtoPostDto) {
        Produto produto = new Produto(produtoPostDto);
        this.produtoRepository.cadastraProduto(produto);
        return new ProdutoResponseCadastroDto(produto);
    }

    @Override
    public ProdutoResponseUpdateEstoqueDto atualizaEstoque(String id, ProdutoPutDto produtoPutDto) {
        Produto produto = this.getProdutoEntity(id);
        produto.incrementarEstoque(produtoPutDto.getQuantity());
        return new ProdutoResponseUpdateEstoqueDto(produto);
    }

    @Override
    public ProdutoResponseRelatorioDto geraRelatorio() {
        return null;
    }

    private Produto getProdutoEntity(String id) {
        Produto produto = this.produtoRepository.getProduto(id);
        if (produto == null) {
            throw new IllegalArgumentException(); // Jogar aq o erro
        }
        return produto;
    }
}
