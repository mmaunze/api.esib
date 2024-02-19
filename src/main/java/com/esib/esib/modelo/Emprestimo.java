
package com.esib.esib.modelo;

import javax.persistence.CascadeType;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static javax.persistence.TemporalType.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(catalog = "esib", schema = "public")
@XmlRootElement
@Data
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_emprestimo", nullable = false)
    private Long idEmprestimo;

    @Basic(optional = false)
    @Column(name = "data_emprestimo", nullable = false)
    @Temporal(TIMESTAMP)
    private Date dataEmprestimo;

    @Basic(optional = false)
    @Column(name = "data_para_devolucao", nullable = false)
    @Temporal(TIMESTAMP)
    private Date dataParaDevolucao;

    @Basic(optional = false)
    @Column(nullable = false)
    private int atraso;

    @JoinColumn(name = "id_bibliotecario", referencedColumnName = "id_bibliotecario", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Bibliotecario idBibliotecario;

    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Estado idEstado;

    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Obra idObra;

    @JoinColumn(name = "id_utilizador", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Utilizador idUtilizador;

    @OneToMany(cascade = ALL, mappedBy = "idEmprestimo", fetch = LAZY)
    private List<Multa> multaList;

    @OneToMany(cascade = ALL, mappedBy = "idEmprestimo", fetch = LAZY)
    private List<Devolucao> devolucaoList;

    public boolean isAtivo() {
        return this.idEstado.getDescricao().equalsIgnoreCase("activo");
    }

    public void setAtivo(boolean b) {
        if (b)
            this.idEstado.setDescricao("activo");
        else
            this.idEstado.setDisponivel();
    }

}
