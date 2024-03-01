
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
@Table(catalog = "esib", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "descricao" }) })
@XmlRootElement
@Data
public class Estado implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(Estado.class.getName());

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado", nullable = false)
    private Long id;

    /**
     *
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String descricao;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "estado", fetch = LAZY)
    private List<Emprestimo> emprestimoList;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "estado", fetch = LAZY)
    private List<Multa> multaList;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "estado", fetch = LAZY)
    private List<Obra> obraList;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "estado", fetch = LAZY)
    private List<Reserva> reservaList;

    /**
     *
     */
    public void setPaga() {
        this.setDescricao("paga");
    }

    /**
     *
     */
    public void setActiva() {
        this.setDescricao("activa");
    }

    /**
     *
     * @return
     */
    public boolean isAciva() {
        return this.getDescricao().equalsIgnoreCase("activa");
    }

    /**
     *
     */
    public void setDisponivel() {
        this.setDescricao("disponivel");

    }


}
