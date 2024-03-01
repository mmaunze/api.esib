package com.esib.esib.modelo.dto;

import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data
public class LivroDTO {
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

    // Atributos epecificos do Livro
    private String editora;
    private String isbn;
    private int edicao;
    private int volume;
    private static final Logger LOG = Logger.getLogger(LivroDTO.class.getName());

}
