package com.esib.esib.dto;

import lombok.Data;

@Data

public class UtilizadorDTO {

    private Long id;
    private String nome;
    private long contacto;
    private String email;
    private Character sexo;
    private String username;
    private AreaCientificaDTO areaCientifica;
    private TipoUtilizadorDTO tipoUtilizador;
    private DepartamentoDTO departamento;

}