package com.esib.esib.modelo.dto;
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
    private static final Logger LOG = Logger.getLogger(ObraDTO.class.getName());

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
    private  String autores;

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


}
