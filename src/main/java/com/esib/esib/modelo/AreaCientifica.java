
package com.esib.esib.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Basic;
import static javax.persistence.CascadeType.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.*;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Entity
@Table(name = "area_cientifica", catalog = "esib", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "descricao" }) })
@Data
@XmlRootElement
public class AreaCientifica implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(AreaCientifica.class.getName());

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_area", nullable = false)
    private Long id;

    /**
     *
     */
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "areaCientifica", fetch = LAZY)
    private List<Obra> obraList;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "areaCientifica", fetch = LAZY)
    private List<Curso> cursoList;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "areaCientifica", fetch = LAZY)
    private List<Utilizador> utilizadorList;


}