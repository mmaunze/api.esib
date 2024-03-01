package com.esib.esib.modelo.dto;

import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data

public class IdiomaDTO {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(IdiomaDTO.class.getName());

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String descricao;

}
