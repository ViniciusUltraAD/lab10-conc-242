package com.example.concorrente_lab10.service.implementation;

import com.example.concorrente_lab10.exceptions.ProdutoExceptions.ProdutoIdJaExiste;
import com.example.concorrente_lab10.exceptions.ProdutoExceptions.ProdutoNotFound;
import com.example.concorrente_lab10.exceptions.ProdutoExceptions.ProdutoQuantitadeInsuficiente;
import com.example.concorrente_lab10.models.Dto.*;
import com.example.concorrente_lab10.models.Produto;
import com.example.concorrente_lab10.repository.ProdutoRepository;
import com.example.concorrente_lab10.service.produtoInterface.ProdutoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<ProdutoGetDto> getTodosProdutos() {
        lock.readLock().lock();
        try {
            return this.produtoRepository.getTodosProdutos().stream().map(ProdutoGetDto::new).toList();
        } finally {
            lock.readLock().unlock();
        }
    }

    //Essa função assim como algumas outras, não deveria ser assíncrona?
    @Override
    public ProdutoGetDto getProduto(String id) {
        lock.readLock().lock();
        try {
            Produto produto = this.getProdutoEntity(id);
            return new ProdutoGetDto(produto); // isso pode sair do lock? //Response: Provavelmente sim.
        } finally {
            lock.readLock().unlock();
        }

    }

    @Override
    @Transactional
    public ProdutoResponseCompraDto compraProduto(ProdutoCompraDto produtoCompraDto) {
        lock.writeLock().lock();
        try{
            Produto produto = this.getProdutoEntity(produtoCompraDto.getId());
            //Precisa refatorar? Quando o professor retorna, ele coloca a disponibilidade também.
            if (produto.getQuantity().get() < produtoCompraDto.getQuantity()) {
                throw new ProdutoQuantitadeInsuficiente("Estoque insuficiente.");
            }
            produto.compraRealizada(produtoCompraDto.getQuantity());
            return new ProdutoResponseCompraDto(produto);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public ProdutoResponseCadastroDto cadastraProduto(ProdutoPostDto produtoPostDto) {
        Produto produto = new Produto(produtoPostDto);
        lock.writeLock().lock();
        try {
            if(this.produtoRepository.containsProduto(produtoPostDto.getId())) {
                throw new ProdutoIdJaExiste();
            }
            this.produtoRepository.salvaProduto(produto);
            return new ProdutoResponseCadastroDto(produto);
        } finally {
            lock.writeLock().unlock();
        }
    }

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

    //TODO
    @Override
    public ProdutoResponseRelatorioDto geraRelatorio() {
        lock.readLock().lock();
        try{
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    private Produto getProdutoEntity(String id) {
        Produto produto = this.produtoRepository.getProduto(id);
        if (produto == null) {
            throw new ProdutoNotFound();
        }
        return produto;
    }
}
