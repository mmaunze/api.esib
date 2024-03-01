package com.esib.esib.modelo.dto;

import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data

public class UtilizadorDTO {

    private Long id;
    private String nome;
    private long contacto;
    private String email;
    private Character sexo;
    private String username;
    private String areaCientifica;
    private String tipoUtilizador;
    private String departamento;
    private static final Logger LOG = Logger.getLogger(UtilizadorDTO.class.getName());

}