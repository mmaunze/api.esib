package com.esib.esib.modelo.dto;

import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data

public class CursoDTO {
    private Long id;
    private String descricao;
    private String faculdade;
    private static final Logger LOG = Logger.getLogger(CursoDTO.class.getName());

}
