
package com.esib.esib.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(catalog = "esib", schema = "public")
@XmlRootElement
@Data
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reserva", nullable = false)
    private Long idReserva;
    @Basic(optional = false)
    @Column(name = "data_reserva", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataReserva;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estado idEstado;
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Obra idObra;
    @JoinColumn(name = "id_utilizador", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilizador idUtilizador;

}
