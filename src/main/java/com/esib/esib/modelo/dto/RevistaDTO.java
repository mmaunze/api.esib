package com.esib.esib.modelo.dto;

import lombok.Data;

@Data
public class RevistaDTO {
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
    // Atributos epecificos da Revista
    private String editora;
    private int issn;
    private String nome;
    private int numero;
    private Long volume;

}
