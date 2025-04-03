package com.example.concorrente_lab10.repository;

import com.example.concorrente_lab10.models.Produto;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ProdutoRepository {

    private ConcurrentHashMap<String, Produto> produtoService;

    public void cadastraProduto(Produto produto) {
        this.produtoService.put(produto.getId(), produto);
    }

    public Produto getProduto(String id){
        return this.produtoService.get(id);
    }

    public List<Produto> getTodosProdutos() {
        return null;
    }
}
