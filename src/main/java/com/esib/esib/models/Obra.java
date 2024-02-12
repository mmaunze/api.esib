package com.esib.esib.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "obra")
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Obra {

    @Id // Identifica a propriedade como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use geração automática de ID
    @Column(name = "id_obra")
    private Integer idObra;

    @Column(name = "autor1", nullable = false, length = 70)
    private String autor1;

    @Column(name = "autor2", length = 70)
    private String autor2;

    @Column(name = "autor3", length = 70)
    private String autor3;

    @Column(name = "titulo", nullable = false, length = 70)
    private String titulo;

    @Column(name = "ano_publicacao", nullable = false)
    private Integer anoPublicacao;

    @ManyToOne(fetch = FetchType.EAGER) // Opcionalmente, especifique aqui o comportamento de carregamento
    @JoinColumn(name = "id_idioma", nullable = false)
    private Idioma idioma;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @Column(name = "nr_paginas", nullable = false)
    private Integer nrPaginas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_area", nullable = false)
    private AreaCientifica areaCientifica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_obra", nullable = false)
    private TipoObra tipoObra;

    @Column(name = "local_publicacao", nullable = false, length = 70)
    private String localPublicacao;

    @Column(name = "assunto", length = 4000)
    private String assunto;

    @Column(name = "localizacao", unique = true, length = 200)
    private String localizacao;

    @Column(name = "fotografia", length = 90)
    private String fotografia;

    @Column(name = "referencia", length = 30)
    private String referencia;

}
