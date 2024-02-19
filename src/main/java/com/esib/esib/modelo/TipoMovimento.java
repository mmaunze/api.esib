
package com.esib.esib.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Entity
@Table(name = "tipo_movimento", catalog = "esib", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "descricao" }) })
@XmlRootElement
@Data
public class TipoMovimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_movimento", nullable = false)
    private Long idTipoMovimento;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String descricao;
    @OneToMany(cascade = ALL, mappedBy = "idTipoMovimento", fetch = LAZY)
    private List<Movimento> movimentoList;
    
    public boolean getPermiteEmprestimo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPermiteEmprestimo'");
    }

}
