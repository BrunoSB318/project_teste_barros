package com.rns.testes.java.dto;

import lombok.Data;

@Data
public class EstoqueDto {

    private Long id;
    private Long filialId;
    private String produtoId;
    private long quantidade;

}
