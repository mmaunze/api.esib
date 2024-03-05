package com.esib.esib.model.dto;

import java.util.Date;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data
public class MovimentoDTO {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(MovimentoDTO.class.getName());

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Long utilizador;

    /**
     *
     */
    private String nomeUtilizador;

    /**
     *
     */
    private Long bibliotecario;

    /**
     *
     */
    private String nomeBibliotecario;

    /**
     *
     */
    private String tipoMovimento;

    /**
     *
     */
    private Long obra;

    /**
     *
     */
    private Date dataMovimento;

    /**
     *
     */
    private String observacao;

}
