package com.esib.esib.modelo.dto;

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
    private static final Logger LOG = Logger.getLogger(TipoUtilizadorDTO.class.getName());

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String descricao;

}
