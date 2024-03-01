package com.esib.esib.modelo.dto;

import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data
public class BibliotecarioDTO {
    private Long id;
    private String nome;
    private Long contacto;
    private String email;
    private Character sexo;
    private String username;
    private String areaCientifica;
    private String departamento;
    private String faculdade;
    private static final Logger LOG = Logger.getLogger(BibliotecarioDTO.class.getName());

}
