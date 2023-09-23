package site.deiv70.springboot.prototype.domain.model.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PrototypeIdNotExistsValidation.class)
public @interface ValidPrototypeIdNotExists {
	String message() default "Prototype ID: '${validatedValue}' Already Exists";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
