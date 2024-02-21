package com.esib.esib.modelo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PagamentoMultaDTO {
    private Long id;
    private Long multa;
    private Date dataPagamento;
private Long bibliotecario;
private String nomeBibliotecario;
    private Double valorPago;
}
