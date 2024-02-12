package com.esib.esib.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "area_cientifica")
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class AreaCientifica {

    @Id // Identifica a propriedade como chave primária
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "areaCientificaSequenceGenerator")
    @GenericGenerator(name = "areaCientificaSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
        @org.hibernate.annotations.Parameter(name = "sequence_name", value = "area_cientifica_id_area_seq"),
        @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
        @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
    })
    @Column(name = "id_area")
    private Integer idArea;

    @Column(name = "descricao", nullable = false, unique = true)
    private String descricao;

}
