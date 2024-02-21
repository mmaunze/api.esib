package com.esib.esib.modelo.dto;

import lombok.Data;

@Data

public class UtilizadorDTO {

    private Long id;
    private String nome;
    private long contacto;
    private String email;
    private Character sexo;
    private String username;
    private String areaCientifica;
    private String tipoUtilizador;
    private String departamento;

}