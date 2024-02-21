package com.esib.esib.modelo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmprestimoDTO {

    private Long id;
    private Integer atraso;
    private Date dataEmprestimo;
    private Date dataParaDevolucao;

    private Long bibliotecario;
    private String bibliotecarioNome;
    
    private String estado;
    private Long obra;
    private String tituloObra;

    private Long utilizador;
    private String utlizadorNome;

}
