package com.esib.esib.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.*;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Entity
@Table(catalog = "esib", schema = "public")
@XmlRootElement
@Data
public class Emprestimo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private static final Logger logger = Logger.getLogger(Emprestimo.class.getName());

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_emprestimo", nullable = false)
    private Long id;

    /**
     *
     */
    @Basic(optional = false)
    @Column(name = "data_emprestimo", nullable = false)
    @Temporal(TIMESTAMP)
    private Date dataEmprestimo;

    /**
     *
     */
    @Basic(optional = false)
    @Column(name = "data_para_devolucao", nullable = false)
    @Temporal(TIMESTAMP)
    private Date dataParaDevolucao;

    /**
     *
     */
    @Basic(optional = false)
    @Column(nullable = false)
    private int atraso;

    /**
     *
     */
    @JoinColumn(name = "id_bibliotecario", referencedColumnName = "id_bibliotecario", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = {PERSIST, MERGE})
    private Bibliotecario bibliotecario;

    /**
     *
     */
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = {PERSIST, MERGE})
    private Estado estado;

    /**
     *
     */
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = {PERSIST, MERGE})
    private Obra obra;

    /**
     *
     */
    @JoinColumn(name = "id_utilizador", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = {PERSIST, MERGE})
    private Utilizador utilizador;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "emprestimo", fetch = LAZY)
    private List<Multa> multaList;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "emprestimo", fetch = LAZY)
    private List<Devolucao> devolucaoList;

    /**
     *
     * @return
     */
    public boolean isAtivo() {
        return this.estado.getDescricao().equalsIgnoreCase("activo");
    }

    /**
     *
     * @param b
     */
    public void setAtivo(boolean b) {
        if (b) {
            this.estado.setDescricao("activo");
        } else {
            this.estado.setDisponivel();
        }
    }

}
