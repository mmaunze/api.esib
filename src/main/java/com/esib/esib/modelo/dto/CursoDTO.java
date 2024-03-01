package com.esib.esib.modelo.dto;

import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data

public class CursoDTO {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(CursoDTO.class.getName());

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
    private String faculdade;

}
