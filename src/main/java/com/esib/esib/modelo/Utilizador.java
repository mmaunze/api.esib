
package com.esib.esib.modelo;

import javax.persistence.CascadeType;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Entity
@Table(catalog = "esib", schema = "public", uniqueConstraints = {
                @UniqueConstraint(columnNames = { "contacto" }),
                @UniqueConstraint(columnNames = { "email" }),
                @UniqueConstraint(columnNames = { "username" }) })
@XmlRootElement
@Data
public class Utilizador implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @Basic(optional = false)
        @Column(name = "id_utilizador", nullable = false)
        private Long idUtilizador;

        @Basic(optional = false)
        @Column(nullable = false, length = 255)
        private String nome;

        @Basic(optional = false)
        @Column(nullable = false)
        private long contacto;

        @Basic(optional = false)
        @Column(nullable = false, length = 255)
        private String email;

        @Basic(optional = false)
        @Column(nullable = false)
        private Character sexo;

        @Column(length = 70)
        private String username;

        @Column(length = 70)
        private String senha;

        @OneToMany(cascade = ALL, mappedBy = "idUtilizador", fetch = LAZY)
        private List<Emprestimo> emprestimoList;

        @OneToOne(cascade = ALL, mappedBy = "utilizador", fetch = LAZY)
        private Cta cta;

        @JoinColumn(name = "id_area", referencedColumnName = "id_area", nullable = false)
        @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
        private AreaCientifica idArea;

        @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento", nullable = false)
        @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
        private Departamento idDepartamento;

        @JoinColumn(name = "id_tipo_utilizador", referencedColumnName = "id_tipo_utilizador", nullable = false)
        @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
        private TipoUtilizador idTipoUtilizador;

        @OneToOne(cascade = ALL, mappedBy = "utilizador", fetch = LAZY)
        private Bibliotecario bibliotecario;
        @OneToOne(cascade = ALL, mappedBy = "utilizador", fetch = LAZY)
        private Estudante estudante;

        @OneToMany(cascade = ALL, mappedBy = "idUtilizador", fetch = LAZY)
        private List<Reserva> reservaList;

        @OneToMany(mappedBy = "idUtilizador", fetch = LAZY)
        private List<Movimento> movimentoList;

}
