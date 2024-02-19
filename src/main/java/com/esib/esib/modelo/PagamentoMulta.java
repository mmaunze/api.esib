
package com.esib.esib.modelo;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static javax.persistence.TemporalType.*;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(name = "pagamento_multa", catalog = "esib", schema = "public")
@XmlRootElement
@Data
public class PagamentoMulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pagamento", nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "valor_pago", nullable = false)
    private double valorPago;

    @Column(name = "data_pagamento")
    @Temporal(TIMESTAMP)
    private Date dataPagamento;

    @JoinColumn(name = "id_bibliotecario", referencedColumnName = "id_bibliotecario", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Bibliotecario bibliotecario;

    @JoinColumn(name = "id_multa", referencedColumnName = "id_multa", nullable = false)
    @ManyToOne(optional = false, fetch = LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Multa multa;

}
