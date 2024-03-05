package com.esib.esib.model.dto;

import java.util.logging.Logger;

import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data
public class ObraDTO {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(ObraDTO.class.getName());

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String titulo;

    /**
     *
     */
    private String autores;

    /**
     *
     */
    private Integer nrPaginas;

    /**
     *
     */
    private String localPublicacao;

    /**
     *
     */
    private Integer anoPublicacao;

    /**
     *
     */
    private String areaCientifica;

    /**
     *
     */
    private String localizacao;

    /**
     *
     */
    private String tipoObra;

    /**
     *
     */
    private String fotografia;

    /**
     *
     */
    private String idioma;

    /**
     *
     */
    private String estado;

    /**
     *
     */
    private String referencia;

}
