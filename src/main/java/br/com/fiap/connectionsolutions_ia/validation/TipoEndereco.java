package br.com.fiap.connectionsolutions_ia.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoEnderecoValidator.class)
public @interface TipoEndereco {
    String message() default "{endereco.tipo}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
