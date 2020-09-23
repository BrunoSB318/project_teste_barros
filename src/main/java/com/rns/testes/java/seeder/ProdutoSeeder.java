package com.rns.testes.java.seeder;

import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.service.IEstoqueService;
import com.rns.testes.java.service.IFilialService;
import com.rns.testes.java.service.IProdutoService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProdutoSeeder {

    @Autowired
    IProdutoService service;
    
    @Autowired
    IEstoqueService serviceEstoque;
    @Autowired
    IFilialService serviceFilial;

    @EventListener
    public void seedFilial(ContextRefreshedEvent event) {
        try {
            log.info("Criando produtos....");
            criandoFiliais();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void criandoFiliais() {
        for (int i = 1; i < 25; i++) {
            Produto produto = new Produto();
            produto.setId("Cod-Produto-"+i);
            produto.setNome("Sal produto " + i);
            service.save(produto);
        }
        
        criandoEstoques();
    }
    
    private void criandoEstoques() {
    	 log.info("Criando esqtoque....");
        List<Filial> findAll = serviceFilial.findAll();
        Produto findById = service.findById("Cod-Produto-1");
        
        long qtd = 1;
        for (Filial filial : findAll) {
        	 log.info("Criando produtos...."+findById.getNome());
        	qtd++;
        	Estoque estoque = new Estoque();
        	estoque.setProduto(findById);
        	estoque.setFilial(filial);
        	estoque.setQuantidade(qtd*qtd);
        	serviceEstoque.save(estoque);
		}
        
        
    }
}
