package com.esib.esib.model.dto;

import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data
public class DepartamentoDTO {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(DepartamentoDTO.class.getName());

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
