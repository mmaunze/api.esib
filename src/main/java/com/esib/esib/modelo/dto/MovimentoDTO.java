package com.esib.esib.modelo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MovimentoDTO {
    private Long id;
    private Long utilizador;
    private String nomeUtilizador;

    private Long bibliotecario;
    private String nomeBibliotecario;

    private String tipoMovimento;

    private Long obra;

    private Date dataMovimento;

    private String observacao;
}
