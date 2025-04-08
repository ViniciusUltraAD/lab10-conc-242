package com.example.concorrente_lab10.service.produtoInterface;

import com.example.concorrente_lab10.models.Dto.*;

import java.util.List;

/**
 * Interface que define os serviços disponíveis para manipulação de produtos.
 */
public interface ProdutoService {

    /**
     * Recupera todos os produtos cadastrados.
     *
     * @return lista de {@link ProdutoGetDto} com todos os produtos disponíveis no sistema.
     */
    List<ProdutoGetDto> getTodosProdutos();

    /**
     * Recupera um produto específico pelo ID.
     *
     * @param id identificador do produto a ser recuperado.
     * @return {@link ProdutoGetDto} contendo os dados do produto correspondente.
     */
    ProdutoGetDto getProduto(String id);

    /**
     * Realiza a compra de uma quantidade de determinado produto.
     *
     * @param produtoCompraDto objeto contendo o ID do produto e a quantidade desejada.
     * @return {@link ProdutoResponseCompraDto} com mensagem e dados atualizados do produto.
     */
    ProdutoResponseCompraDto compraProduto(ProdutoCompraDto produtoCompraDto);

    /**
     * Cadastra um novo produto no sistema.
     *
     * @param produtoPostDto dados do produto a ser cadastrado.
     * @return {@link ProdutoResponseCadastroDto} com mensagem de sucesso e dados do produto.
     */
    ProdutoResponseCadastroDto cadastraProduto(ProdutoPostDto produtoPostDto);

    /**
     * Atualiza o estoque de um produto já existente.
     *
     * @param id identificador do produto a ser atualizado.
     * @param produtoPutDto objeto contendo a nova quantidade para o estoque.
     * @return {@link ProdutoResponseUpdateEstoqueDto} com mensagem de atualização e estoque restante.
     */
    ProdutoResponseUpdateEstoqueDto atualizaEstoque(String id, ProdutoPutDto produtoPutDto);

    /**
     * Gera um relatório de vendas com a quantidade vendida por produto.
     *
     * @return {@link RelatorioVendasResponseDto} com o total de vendas e lista dos produtos vendidos.
     */
    RelatorioVendasResponseDto geraRelatorio();

}
