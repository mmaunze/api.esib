
package com.esib.esib.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Entity
@Table(catalog = "esib", schema = "public")
@XmlRootElement
@Data
public class Monografia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_obra", nullable = false)
    private Long idObra;

    @Basic(optional = false)
    @Column(nullable = false, length = 70)
    private String supervisor;

    @Column(name = "co_supervisor", length = 70)
    private String coSupervisor;

    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY)
    private Curso idCurso;

    @JoinColumn(name = "id_faculdade", referencedColumnName = "id_faculdade", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY)
    private Faculdade idFaculdade;
    
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = LAZY)
    private Obra obra;

}
