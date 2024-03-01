package com.esib.esib.modelo.enums;


import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProfileEnum {

    BIBLIOTECARIO(1,"ROLE_BIBLIOTECARIO"),
    ESTUDANTE(2,"ROLE_ESTUDANTE"),
    DOCENTE(3,"ROLE_DOCENTE"),
    CTA(4,"ROLE_CTA"),
    ADMINISTRADOR(5,"ROLE_ADMINISTRADOR");

    private Integer code;
    private String description;

    public static ProfileEnum toEnum(Integer code) {
        if (Objects.isNull(code))
            return null;

        for (ProfileEnum x : ProfileEnum.values()) {
            if (code.equals(x.getCode()))
                return x;
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }

}