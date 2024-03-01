package com.esib.esib.modelo.dto;

import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data
public class DepartamentoDTO {
    private Long id;
    private String descricao;
    private String sigla;
    private static final Logger LOG = Logger.getLogger(DepartamentoDTO.class.getName());
}
