package com.rns.testes.java.service.impl;

import org.springframework.stereotype.Service;

import com.rns.testes.java.dao.IEstoqueDao;
import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.service.AbstractGenericServicePersistence;
import com.rns.testes.java.service.IEstoqueService;

@Service
public class EstoqueService extends AbstractGenericServicePersistence<IEstoqueDao, Estoque, Long> implements IEstoqueService {
}
