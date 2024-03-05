package com.esib.esib.model.dto;

import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data

public class TipoObraDTO {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(TipoObraDTO.class.getName());

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String descricao;

}
