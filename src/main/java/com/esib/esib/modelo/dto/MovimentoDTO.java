package com.esib.esib.modelo.dto;

import java.util.Date;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data
public class MovimentoDTO {
    private Long id;
    private Long utilizador;
    private String nomeUtilizador;

    private Long bibliotecario;
    private String nomeBibliotecario;

    private String tipoMovimento;

    private Long obra;

    private Date dataMovimento;

    private String observacao;
    private static final Logger LOG = Logger.getLogger(MovimentoDTO.class.getName());
}
