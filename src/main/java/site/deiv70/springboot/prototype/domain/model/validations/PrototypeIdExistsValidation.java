package site.deiv70.springboot.prototype.domain.model.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class PrototypeIdExistsValidation implements ConstraintValidator<ValidPrototypeIdExists, UUID> {

	private final PrototypeRepositoryPort prototypeRepositoryPort;

	@Override
	public void initialize(ValidPrototypeIdExists constraintAnnotation) {
		// Initialization, if needed
	}

	@Override
	public boolean isValid(final UUID prototypeId, ConstraintValidatorContext context) {
		Optional<PrototypeModel> prototypeModel = prototypeRepositoryPort.getPrototypeById(prototypeId);

		return prototypeModel.isPresent() && prototypeModel.get().getId() == prototypeId;
	}
}
