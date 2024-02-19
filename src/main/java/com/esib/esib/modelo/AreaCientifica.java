
package com.esib.esib.modelo;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(name = "area_cientifica", catalog = "esib", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "descricao" }) })
@Data
@XmlRootElement
public class AreaCientifica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_area", nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @OneToMany(cascade = ALL, mappedBy = "areaCientifica", fetch = LAZY)
    
    private List<Obra> obraList;

    @OneToMany(cascade = ALL, mappedBy = "areaCientifica", fetch = LAZY)
    private List<Utilizador> utilizadorList;

}