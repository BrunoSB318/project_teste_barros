package com.rns.testes.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rns.testes.java.dto.EstoqueDto;
import com.rns.testes.java.dto.EstoqueTransferenciaDto;
import com.rns.testes.java.dto.mapper.EstoqueMapper;
import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.service.IEstoqueService;
import com.rns.testes.java.service.IFilialService;
import com.rns.testes.java.service.IProdutoService;

@CrossOrigin
@RestController
@RequestMapping
public class EstoqueController {

    private static final String BASE_URL = "estoque/";

    @Autowired
    IEstoqueService service;
    @Autowired
    IFilialService serviceFilial;
    @Autowired
    IProdutoService serviceProduto;
    

    @GetMapping(value = BASE_URL + "find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Estoque>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = BASE_URL + "find-by-id", produces = MediaType.APPLICATION_JSON_VALUE, params = {"id"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Estoque> findById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    

    @PutMapping(value = BASE_URL + "update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Estoque> update(@RequestBody EstoqueDto dto) {
    	Long idFilial = dto.getFilialId();
    	Filial filial = serviceFilial.findById(idFilial);
    	Estoque estoque = service.findById(dto.getId());
    	estoque.setFilial(filial);
    	Produto produto = serviceProduto.findById(dto.getProdutoId());
    	estoque.setProduto(produto);
    	estoque.setQuantidade(dto.getQuantidade());
        return ResponseEntity.ok(service.update(estoque));
    }

    @PutMapping(value = BASE_URL + "transferir", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Estoque> transferir(@RequestBody EstoqueTransferenciaDto dto) {
    	Long idEstoque = dto.getIdEstoque();
    	Long idFilial = dto.getIdFilialPara();
    	Estoque estoque = service.findById(idEstoque);
    	Filial filial = serviceFilial.findById(idFilial);
    	estoque.setFilial(filial);
        return ResponseEntity.ok(service.update(estoque));
    }

    @PostMapping(value = BASE_URL + "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Estoque> insert(@RequestBody EstoqueDto dto) {
    	Long idFilial = dto.getFilialId();
    	Filial filial = serviceFilial.findById(idFilial);
    	Estoque estoque = new Estoque();
    	estoque.setFilial(filial);
    	Produto produto = serviceProduto.findById(dto.getProdutoId());
    	estoque.setProduto(produto);
    	estoque.setQuantidade(dto.getQuantidade());
        return ResponseEntity.ok(service.save(estoque));
    }

    @DeleteMapping(value = BASE_URL + "delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@RequestParam(name = "id") Long id) {
        service.delete(id);
    }

}
