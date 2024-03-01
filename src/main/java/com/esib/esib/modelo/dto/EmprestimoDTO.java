package com.esib.esib.modelo.dto;

import java.util.Date;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data
public class EmprestimoDTO {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(EmprestimoDTO.class.getName());

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Integer atraso;

    /**
     *
     */
    private Date dataEmprestimo;

    /**
     *
     */
    private Date dataParaDevolucao;

    /**
     *
     */
    private Long bibliotecario;

    /**
     *
     */
    private String bibliotecarioNome;
    
    /**
     *
     */
    private String estado;

    /**
     *
     */
    private Long obra;

    /**
     *
     */
    private String tituloObra;

    /**
     *
     */
    private Long utilizador;

    /**
     *
     */
    private String utlizadorNome;


}
