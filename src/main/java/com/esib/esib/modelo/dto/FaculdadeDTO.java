package com.esib.esib.modelo.dto;

import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data

public class FaculdadeDTO {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(FaculdadeDTO.class.getName());

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String descricao;

    /**
     *
     */
    private String sigla;

}
