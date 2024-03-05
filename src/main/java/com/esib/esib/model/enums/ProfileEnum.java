package com.esib.esib.model.enums;


import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Meldo Maunze
 */
@AllArgsConstructor
@Getter
public enum ProfileEnum {

    /**
     *
     */
    BIBLIOTECARIO(1,"ROLE_BIBLIOTECARIO"),

    /**
     *
     */
    ESTUDANTE(2,"ROLE_ESTUDANTE"),

    /**
     *
     */
    DOCENTE(3,"ROLE_DOCENTE"),

    /**
     *
     */
    CTA(4,"ROLE_CTA"),

    /**
     *
     */
    ADMINISTRADOR(5,"ROLE_ADMINISTRADOR");

    /**
     *
     */
    private Integer code;

    /**
     *
     */
    private String description;

    /**
     *
     * @param code
     * @return
     */
    public static ProfileEnum toEnum(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }

        for (var x : ProfileEnum.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }

}