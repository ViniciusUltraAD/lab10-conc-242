package com.example.concorrente_lab10.service.implementation;

import com.example.concorrente_lab10.exceptions.ProdutoExceptions.ProdutoIdJaExiste;
import com.example.concorrente_lab10.exceptions.ProdutoExceptions.ProdutoNotFound;
import com.example.concorrente_lab10.exceptions.ProdutoExceptions.ProdutoQuantitadeInsuficiente;
import com.example.concorrente_lab10.models.Dto.*;
import com.example.concorrente_lab10.models.Produto;
import com.example.concorrente_lab10.repository.ProdutoRepository;
import com.example.concorrente_lab10.service.produtoInterface.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Implementação da interface {@link ProdutoService} que fornece operações de
 * gerenciamento de produtos, como cadastro, compra, consulta e geração de relatório.
 *
 * Usa {@link ReentrantReadWriteLock} para garantir segurança em ambientes concorrentes:
 * leituras simultâneas são permitidas, enquanto escritas exigem exclusividade.
 */
@Service
public class ProdutoServiceImpl implements ProdutoService {

    /**
     * Repositório responsável por armazenar e recuperar objetos do tipo {@link Produto}.
     *
     * Implementado com HashMap.
     */
    private final ProdutoRepository produtoRepository;

    /**
     * Lock do tipo {@link ReentrantReadWriteLock} utilizado para controlar o acesso concorrente
     * aos métodos da classe que leem e modificam o estado dos produtos.
     *
     * - A leitura (com `readLock()`) permite múltiplas threads simultâneas sem bloqueio.<br>
     * - A escrita (com `writeLock()`) garante exclusividade e impede leituras enquanto ocorre a modificação.
     *
     * O construtor recebe o argumento `true` para ativar a política de prioridade justa (fairness),
     * evitando que threads de leitura fiquem presas indefinidamente quando há muitas escritas.
     */
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    /**
     * Construtor que injeta o repositório de produtos.
     *
     * @param produtoRepository Repositório a ser utilizado pela implementação.
     */
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /**
     * Obtém todos os produtos cadastrados no sistema.
     *
     * @return Lista de DTOs com os dados dos produtos.
     */
    @Override
    public List<ProdutoGetDto> getTodosProdutos() {
        lock.readLock().lock();
        try {
            return this.produtoRepository.getTodosProdutos().stream().map(ProdutoGetDto::new).toList();
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Consulta um produto por ID.
     *
     * @param id Identificador do produto.
     * @return DTO com os dados do produto.
     * @throws ProdutoNotFound caso o produto não seja encontrado.
     */
    @Override
    public ProdutoGetDto getProduto(String id) {
        lock.readLock().lock();
        try {
            Produto produto = this.getProdutoEntity(id);
            return new ProdutoGetDto(produto);
        } finally {
            lock.readLock().unlock();
        }

    }

    /**
     * Realiza a compra de uma quantidade específica de um produto.
     *
     * @param produtoCompraDto DTO com ID do produto e quantidade a ser comprada.
     * @return DTO com mensagem de sucesso e estado atual do produto.
     * @throws ProdutoNotFound caso o produto não seja encontrado.
     * @throws ProdutoQuantitadeInsuficiente caso não haja quantidade suficiente em estoque.
     */
    @Override
    public ProdutoResponseCompraDto compraProduto(ProdutoCompraDto produtoCompraDto) {
        lock.writeLock().lock();
        try{
            Produto produto = this.getProdutoEntity(produtoCompraDto.getId());
            if (produto.getQuantity() < produtoCompraDto.getQuantity()) {
                throw new ProdutoQuantitadeInsuficiente(produto.getQuantity());
            }
            produto.compraRealizada(produtoCompraDto.getQuantity());
            return new ProdutoResponseCompraDto(produto);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Cadastra um novo produto no sistema.
     *
     * @param produtoPostDto DTO com os dados do produto.
     * @return DTO de resposta com mensagem de sucesso e produto cadastrado.
     * @throws ProdutoIdJaExiste caso o ID já esteja cadastrado.
     */
    @Override
    public ProdutoResponseCadastroDto cadastraProduto(ProdutoPostDto produtoPostDto) {
        Produto produto = new Produto(produtoPostDto);
        lock.writeLock().lock();
        try {
            if(this.produtoRepository.containsProduto(produtoPostDto.getId())) {
                throw new ProdutoIdJaExiste();
            }
            this.produtoRepository.salvaProduto(produto);
        } finally {
            lock.writeLock().unlock();
        }
        return new ProdutoResponseCadastroDto(produto);
    }

    /**
     * Atualiza o estoque de um produto com nova quantidade.
     *
     * @param id ID do produto a ser atualizado.
     * @param produtoPutDto DTO com a nova quantidade.
     * @return DTO com mensagem de sucesso e estoque atualizado.
     * @throws ProdutoNotFound caso o produto não seja encontrado.
     */
    @Override
    public ProdutoResponseUpdateEstoqueDto atualizaEstoque(String id, ProdutoPutDto produtoPutDto) {
        lock.writeLock().lock();
        try {
            Produto produto = this.getProdutoEntity(id);
            produto.incrementarEstoque(produtoPutDto.getQuantity());
            this.produtoRepository.salvaProduto(produto);
            return new ProdutoResponseUpdateEstoqueDto(produto);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Gera um relatório com as vendas realizadas.
     *
     * @return DTO com a lista de produtos vendidos e o total de vendas.
     */
    @Override
    public RelatorioVendasResponseDto geraRelatorio() {
        lock.readLock().lock();
        try{
            List<ProdutoResponseRelatorioDto> produtos = this.produtoRepository.getTodosProdutos().stream()
                    .filter(produto -> produto.getCountSold().intValue() > 0)
                    .map(ProdutoResponseRelatorioDto::new)
                    .toList();

            int totalSale = produtos.stream()
                    .mapToInt(ProdutoResponseRelatorioDto::getCountSold)
                    .reduce(0, Integer::sum);

            return new RelatorioVendasResponseDto(totalSale, produtos);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Recupera um produto pelo ID diretamente do repositório.
     *
     * @param id ID do produto.
     * @return Produto correspondente.
     * @throws ProdutoNotFound caso não exista produto com o ID informado.
     */
    private Produto getProdutoEntity(String id) {
        Produto produto = this.produtoRepository.getProduto(id);
        if (produto == null) {
            throw new ProdutoNotFound();
        }
        return produto;
    }
}
