package com.example.concorrente_lab10.repository;

import com.example.concorrente_lab10.models.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProdutoRepository {

    private final ConcurrentHashMap<String, Produto> produtoConcurrentHashMap;

    public ProdutoRepository() {
        this.produtoConcurrentHashMap = new ConcurrentHashMap<>();
    }
    public void cadastraProduto(Produto produto) {
        this.produtoConcurrentHashMap.put(produto.getId(), produto);
    }

    public Produto getProduto(String id){
        return this.produtoConcurrentHashMap.get(id);
    }

    public List<Produto> getTodosProdutos() {
        return this.produtoConcurrentHashMap.values().stream().toList();
    }
}
