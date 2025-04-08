package com.example.concorrente_lab10.repository;

import com.example.concorrente_lab10.models.Produto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Repositório em memória para manipulação de produtos.
 *
 * Utiliza HashMap como base simulando um banco de dados indexado por índice.
 */
@Repository
public class ProdutoRepository {

    /**
     * Estrutura de dados que armazena os produtos por ID.
     */
    private final HashMap<String, Produto> produtoHashMap;

    /**
     * Construtor padrão que inicializa o {@code HashMap}.
     */
    public ProdutoRepository() {
        this.produtoHashMap = new HashMap<>();
    }

    /**
     * Salva ou atualiza um produto no repositório.
     *
     * @param produto Produto a ser salvo.
     */
    public void salvaProduto(Produto produto) {
        this.produtoHashMap.put(produto.getId(), produto);
    }

    /**
     * Retorna um produto com base no ID.
     *
     * @param id Identificador do produto.
     * @return Produto correspondente ao ID, ou {@code null} se não encontrado.
     */
    public Produto getProduto(String id){
        return this.produtoHashMap.get(id);
    }

    /**
     * Retorna todos os produtos armazenados no repositório.
     *
     * @return Lista com todos os produtos.
     */
    public List<Produto> getTodosProdutos() {
        return this.produtoHashMap.values().stream().toList();
    }

    /**
     * Verifica se um produto com o ID fornecido existe no repositório.
     *
     * @param id ID do produto a ser verificado.
     * @return {@code true} se o produto existe, {@code false} caso contrário.
     */
    public boolean containsProduto(String id) {
        return this.produtoHashMap.containsKey(id);
    }
}
