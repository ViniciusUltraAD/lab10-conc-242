package com.example.concorrente_lab10.repository;

import com.example.concorrente_lab10.models.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repositório em memória para manipulação de produtos.
 *
 * <p>Utiliza {@link ConcurrentHashMap} para permitir operações seguras em ambientes concorrentes.</p>
 */
@Repository
public class ProdutoRepository {

    /**
     * Estrutura de dados que armazena os produtos por ID.
     */
    private final ConcurrentHashMap<String, Produto> produtoConcurrentHashMap;

    /**
     * Construtor padrão que inicializa o {@code ConcurrentHashMap}.
     */
    public ProdutoRepository() {
        this.produtoConcurrentHashMap = new ConcurrentHashMap<>();
    }

    /**
     * Salva ou atualiza um produto no repositório.
     *
     * @param produto Produto a ser salvo.
     */
    public void salvaProduto(Produto produto) {
        this.produtoConcurrentHashMap.put(produto.getId(), produto);
    }

    /**
     * Retorna um produto com base no ID.
     *
     * @param id Identificador do produto.
     * @return Produto correspondente ao ID, ou {@code null} se não encontrado.
     */
    public Produto getProduto(String id){
        return this.produtoConcurrentHashMap.get(id);
    }

    /**
     * Retorna todos os produtos armazenados no repositório.
     *
     * @return Lista com todos os produtos.
     */
    public List<Produto> getTodosProdutos() {
        return this.produtoConcurrentHashMap.values().stream().toList();
    }

    /**
     * Verifica se um produto com o ID fornecido existe no repositório.
     *
     * @param id ID do produto a ser verificado.
     * @return {@code true} se o produto existe, {@code false} caso contrário.
     */
    public boolean containsProduto(String id) {
        return this.produtoConcurrentHashMap.containsKey(id);
    }
}
