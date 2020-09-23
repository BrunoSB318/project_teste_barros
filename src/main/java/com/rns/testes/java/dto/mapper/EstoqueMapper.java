package com.rns.testes.java.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.rns.testes.java.dto.EstoqueDto;
import com.rns.testes.java.model.Estoque;

/**
 * Interface respons?vel por implementar MapperStruct no mapeamento entre Produto e ProdutoDto.
 */
@Mapper
public interface EstoqueMapper extends GenericMapper<Estoque, EstoqueDto> {

    EstoqueMapper INSTANCE = Mappers.getMapper(EstoqueMapper.class);

}
