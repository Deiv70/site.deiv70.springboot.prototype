package site.deiv70.springboot.prototype.domain.model.validations;


import jakarta.validation.*;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PrototypeIdExistsValidation.class)
public @interface ValidPrototypeIdExists {
	String message() default "Prototype ID: '${validatedValue}' Doesn't Exist";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
