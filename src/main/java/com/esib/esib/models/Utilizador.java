package com.esib.esib.models;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "utilizador")
@Data // Lombok annotation para gerar getters, setters e outros métodos
public class Utilizador {

    @Id // Identifica a propriedade como chave primária
    @Column(name = "id_utilizador")
    private String idUtilizador;



    @Column(name = "nome", nullable = false, length = 255)
    @NotNull
    @NotEmpty
    private String nome;

    @Column(name = "contacto", nullable = false, unique=true, length= 9)
    @NotNull
    @NotEmpty
    @Size( min=9, max =9 )
    private Long contacto;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    @NotNull
    @NotEmpty
    private String email;

    @ManyToOne(fetch = FetchType.EAGER) // Opcionalmente, especifique aqui o comportamento de carregamento
    @JoinColumn(name = "id_area", nullable = false)
    @NotNull
    @NotEmpty
    private AreaCientifica areaCientifica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_departamento", nullable = false)
    @NotNull
    @NotEmpty
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_utilizador", nullable = false)
    @NotNull
    @NotEmpty
    private TipoUtilizador tipoUtilizador;

    @Column(name = "sexo", nullable = false, length = 1)
    @NotNull
    @NotEmpty
    private String sexo;

    @Column(name = "username", unique = true, length = 70)
    @NotNull
    @NotEmpty
    private String username;

    @Column(name = "senha", length = 70)
    @NotNull
    @NotEmpty
    @Size(min=8, max=70)
    private String senha;

  @PrePersist
    public void prePersist() {
        if (this.idUtilizador == null || this.idUtilizador.isEmpty()) {
            this.idUtilizador = gerarId(); // Chama o método para gerar o ID se não estiver definido
        }
    }

    private String gerarId() {
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomDigits = String.format("%03d", (int) (Math.random() * 1000));
        char randomChar = (char) ('A' + Math.random() * ('Z' - 'A' + 1));
        return formattedDate + randomDigits + randomChar;
    }

}
