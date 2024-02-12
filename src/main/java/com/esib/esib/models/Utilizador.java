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
@Table(name = "utilizador")
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Utilizador {

    @Id // Identifica a propriedade como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use geração automática de ID
    @Column(name = "id_utilizador")
    private Long idUtilizador;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "contacto", nullable = false)
    private Long contacto;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER) // Opcionalmente, especifique aqui o comportamento de carregamento
    @JoinColumn(name = "id_area", nullable = false)
    private AreaCientifica areaCientifica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_utilizador", nullable = false)
    private TipoUtilizador tipoUtilizador;

    @Column(name = "sexo", nullable = false, length = 1)
    private String sexo;

    @Column(name = "username", unique = true, length = 70)
    private String username;

    @Column(name = "senha", length = 70)
    private String senha;

}
