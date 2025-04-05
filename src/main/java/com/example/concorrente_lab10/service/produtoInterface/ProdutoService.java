package com.example.concorrente_lab10.service.produtoInterface;

import com.example.concorrente_lab10.models.Dto.*;

import java.util.List;

public interface ProdutoService {

    List<ProdutoGetDto> getTodosProdutos();

    ProdutoGetDto getProduto(String id);

    //TODO ver mensagem
    ProdutoResponseCompraDto compraProduto(ProdutoCompraDto produtoCompraDto);

    //TODO ver mensagem
    ProdutoResponseCadastroDto cadastraProduto(ProdutoPostDto produtoPostDto);

    ProdutoResponseUpdateEstoqueDto atualizaEstoque(String id, ProdutoPutDto produtoPutDto);

    ProdutoResponseRelatorioDto geraRelatorio();

}
