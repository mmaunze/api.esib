
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
public class Cta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_utilizador", nullable = false)
    private Long idUtilizador;
    @Column(length = 50)
    private String grau;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY)
    private Cargo idCargo;
    @JoinColumn(name = "id_utilizador", referencedColumnName = "id_utilizador", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = LAZY)
    private Utilizador utilizador;

}
