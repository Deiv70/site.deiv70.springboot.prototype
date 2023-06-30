package site.deiv70.springboot.prototype.application.usecase;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@AllArgsConstructor
@Component
public class UpdatePrototypeByIdUseCase {

	private PrototypeRepositoryPort prototypeRepositoryPort;

	public Optional<PrototypeModel> updatePrototypeById(final PrototypeModel prototypeModel) {
		validate(prototypeModel);
		return prototypeRepositoryPort.updatePrototypeById(prototypeModel);
	}


	private void validate(PrototypeModel prototypeModel) {
		ofNullable(prototypeModel.getId())
			.orElseThrow(IllegalAccessError::new);
		ofNullable(prototypeModel.getName())
			.orElseThrow(IllegalAccessError::new);
		prototypeRepositoryPort.getPrototypeById(prototypeModel.getId())
			.orElseThrow(EntityNotFoundException::new);
	}

}
