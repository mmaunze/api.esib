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
@Table(name = "bibliotecario")
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Bibliotecario {

    @Id // Identifica a propriedade como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use geração automática de ID
    @Column(name = "id_bibliotecario")
    private Long idBibliotecario;

    @ManyToOne(fetch = FetchType.EAGER) // Opcionalmente, especifique aqui o comportamento de carregamento
    @JoinColumn(name = "id_bibliotecario", nullable = false) // Reutiliza id_bibliotecario como chave estrangeira (relação com Utilizador)
    private Utilizador utilizador;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_faculdade", nullable = false)
    private Faculdade faculdade;

}
