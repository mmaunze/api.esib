package com.esib.esib.modelo.dto;

import lombok.Data;

@Data
public class EstudanteDTO {
    private Long id;
    private String nome;
    private Long contacto;
    private String email;
    private Character sexo;
    private String username;
    private String areaCientifica;
    private String departamento;
    private String curso;
    private int nivel;

}
