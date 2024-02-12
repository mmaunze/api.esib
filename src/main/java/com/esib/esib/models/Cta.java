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
@Table(name = "cta")
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Cta {

    @Id // Identifica a propriedade como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use geração automática de ID
    @Column(name = "id_utilizador")
    private Long idUtilizador;

    @ManyToOne(fetch = FetchType.EAGER) // Opcionalmente, especifique aqui o comportamento de carregamento
    @JoinColumn(name = "id_cargo", nullable = false)
    private Cargo cargo;

    @Column(name = "grau", length = 50)
    private String grau;

    // Relação ManyToOne com Utilizador (não é a chave primária)
    @ManyToOne(fetch = FetchType.EAGER) // Opcionalmente, especifique aqui o comportamento de carregamento
    @JoinColumn(name = "id_utilizador", nullable = false) // Reutiliza id_utilizador como chave estrangeira
    private Utilizador utilizador;

}
