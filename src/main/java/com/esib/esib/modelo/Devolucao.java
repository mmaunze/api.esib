
package com.esib.esib.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Entity
@Table(catalog = "esib", schema = "public")
@XmlRootElement
@Data
public class Devolucao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_devolucao", nullable = false)
    private Long idDevolucao;

    @Basic(optional = false)
    @Column(name = "data_devolucao", nullable = false)
    @Temporal(TIMESTAMP)
    private Date dataDevolucao;

    private Long atraso;
    @JoinColumn(name = "id_bibliotecario", referencedColumnName = "id_bibliotecario", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY)
    private Bibliotecario idBibliotecario;
    
    @JoinColumn(name = "id_emprestimo", referencedColumnName = "id_emprestimo", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY)
    private Emprestimo idEmprestimo;

}
