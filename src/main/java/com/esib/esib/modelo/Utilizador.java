package com.esib.esib.modelo;

import com.esib.esib.modelo.enums.ProfileEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.Basic;
import static javax.persistence.CascadeType.*;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @UniqueConstraint(columnNames = {"contacto"}),
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"username"})})
@XmlRootElement
@Data
public class Utilizador implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private static final Logger logger = Logger.getLogger(Utilizador.class.getName());

    /**
     *
     */
    @Id
    @Basic(optional = false)
    @Column(name = "id_utilizador", nullable = false)
    private Long id;

    /**
     *
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String nome;

    /**
     *
     */
    @Basic(optional = false)
    @Column(nullable = false)
    private long contacto;

    /**
     *
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String email;

    /**
     *
     */
    @Basic(optional = false)
    @Column(nullable = false)
    private Character sexo;

    /**
     *
     */
    @Column(length = 70)
    private String username;

    /**
     *
     */
    @Column(length = 70)
    private String password;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "utilizador", fetch = LAZY)
    private List<Emprestimo> emprestimoList;

    /**
     *
     */
    @OneToOne(cascade = ALL, mappedBy = "utilizador", fetch = LAZY)
    private Cta cta;

    /**
     *
     */
    @JoinColumn(name = "id_area", referencedColumnName = "id_area", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = {PERSIST, MERGE})
    private AreaCientifica areaCientifica;

    /**
     *
     */
    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = {PERSIST, MERGE})
    private Departamento departamento;

    /**
     *
     */
    @JoinColumn(name = "id_tipo_utilizador", referencedColumnName = "id_tipo_utilizador", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = {PERSIST, MERGE})
    private TipoUtilizador tipoUtilizador;

    /**
     *
     */
    @OneToOne(cascade = ALL, mappedBy = "utilizador", fetch = LAZY)
    private Bibliotecario bibliotecario;

    /**
     *
     */
    @OneToOne(cascade = ALL, mappedBy = "utilizador", fetch = LAZY)
    private Estudante estudante;

    /**
     *
     */
    @OneToMany(cascade = ALL, mappedBy = "utilizador", fetch = LAZY)
    private List<Reserva> reservaList;

    /**
     *
     */
    @OneToMany(mappedBy = "utilizador", fetch = LAZY)
    private List<Movimento> movimentoList;

    /**
     *
     */
    @Column(name = "profile", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_profile")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> profiles = new HashSet<>();

    /**
     *
     * @return
     */
    public Set<ProfileEnum> getProfiles() {
        return this.profiles.stream().map(x -> ProfileEnum.toEnum(x)).collect(Collectors.toSet());
    }

    /**
     *
     * @param profileEnum
     */
    public void addProfile(ProfileEnum profileEnum) {
        this.profiles.add(profileEnum.getCode());
    }

}
