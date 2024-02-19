
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
@Table(catalog = "esib", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "descricao" }) })
@XmlRootElement
@Data
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado", nullable = false)
    private Long idEstado;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String descricao;
    @OneToMany(cascade = ALL, mappedBy = "idEstado", fetch = LAZY)
    private List<Emprestimo> emprestimoList;
    @OneToMany(cascade = ALL, mappedBy = "idEstado", fetch = LAZY)
    private List<Multa> multaList;
    @OneToMany(cascade = ALL, mappedBy = "idEstado", fetch = LAZY)
    private List<Obra> obraList;
    @OneToMany(cascade = ALL, mappedBy = "idEstado", fetch = LAZY)
    private List<Reserva> reservaList;

    public void setPaga() {
        this.setDescricao("paga");
    }

    public void setActiva() {
        this.setDescricao("activa");
    }

    public boolean isAciva() {
        return this.getDescricao().equalsIgnoreCase("activa");
    }

    public void setDisponivel() {
        this.setDescricao("disponivel");

    }

}
