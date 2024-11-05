package br.com.fiap.connectionsolutions_ia.validation;


import br.com.fiap.connectionsolutions_ia.enums.TipoEnderecoEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoEnderecoValidator implements ConstraintValidator<TipoEndereco, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        try {
            TipoEnderecoEnum.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
