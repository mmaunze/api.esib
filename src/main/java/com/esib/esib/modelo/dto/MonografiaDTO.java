package com.esib.esib.modelo.dto;

import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
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
    private static final Logger LOG = Logger.getLogger(MonografiaDTO.class.getName());

}
