package com.esib.esib.modelo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DevolucaoDTO {

    private Long id;
    private Long atraso;

    private Date dataDevolucao;

    private Long bibliotecario;
    private String bibliotecarioNome;

    private String estado;
    private Long obra;
    private String tituloObra;

    private Long utilizador;
    private String utlizadorNome;

    private Long emprestimo;

}
