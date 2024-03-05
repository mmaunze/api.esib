package com.esib.esib.model.dto;

import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data

public class TipoUtilizadorDTO {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(TipoUtilizadorDTO.class.getName());

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String descricao;

}
