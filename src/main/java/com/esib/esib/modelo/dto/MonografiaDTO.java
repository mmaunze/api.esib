package com.esib.esib.modelo.dto;

import lombok.Data;

@Data
public class MonografiaDTO {
    private Long id;
// Atributos da Obra (genericos)
    private String titulo;
    private String autores;
    private int nrPaginas;
    private String localPublicacao;
    private int anoPublicacao;
    private String idioma;
    private String estado;
    private String areaCientifica;
    private String fotografia;
// Atributos epecificos da monografia
    private String supervisor;
    private String coSupervisor;
    private String curso;
    private String faculdade;

}
