package com.esib.esib.modelo.dto;
import lombok.Data;

@Data
public class ObraDTO {
    private Long id;
    private String titulo;
    private  String autores;
    private Integer nrPaginas;
    private String localPublicacao;
    private Integer anoPublicacao;
    private String areaCientifica;
    private String localizacao;
    private String tipoObra;
    private String fotografia;
    private String idioma;
    private String estado;

}
