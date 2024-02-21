package com.esib.esib.modelo.dto;

import java.util.Date;

import lombok.Data;

@Data

public class ReservaDTO {
    private Long id;
    private Integer atraso;
    private Date dataReserva;

    private String estado;
    private Long obra;
    private String tituloObra;

    private Long utilizador;
    private String utlizadorNome;
}
