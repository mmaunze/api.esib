package com.esib.esib.model.dto;

import java.util.Date;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data
public class DevolucaoDTO {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(DevolucaoDTO.class.getName());

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Long atraso;

    /**
     *
     */
    private Date dataDevolucao;

    /**
     *
     */
    private Long bibliotecario;

    /**
     *
     */
    private String bibliotecarioNome;

    /**
     *
     */
    private String estado;

    /**
     *
     */
    private Long obra;

    /**
     *
     */
    private String tituloObra;

    /**
     *
     */
    private Long utilizador;

    /**
     *
     */
    private String utlizadorNome;

    /**
     *
     */
    private Long emprestimo;

}
