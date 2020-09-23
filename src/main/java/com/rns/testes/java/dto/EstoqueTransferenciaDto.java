package com.rns.testes.java.dto;

import lombok.Data;

@Data
public class EstoqueTransferenciaDto {

	private Long idEstoque;
	private Long idFilialDe;
    private Long idFilialPara;

}
