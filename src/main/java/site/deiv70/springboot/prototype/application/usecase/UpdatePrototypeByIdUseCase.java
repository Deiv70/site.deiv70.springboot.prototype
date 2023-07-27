package site.deiv70.springboot.prototype.application.usecase;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;

import java.util.Optional;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@AllArgsConstructor
@Service
public class UpdatePrototypeByIdUseCase {

	private PrototypeRepositoryPort prototypeRepositoryPort;

	public Optional<PrototypeModel> execute(final UUID prototypeId, final PrototypeModel prototypeModel) {
		// Override the id with the one from the path
		prototypeModel.setId(prototypeId);
		// Validate the prototype
		validate(prototypeModel);
		// Update the prototype
		prototypeRepositoryPort.updatePrototypeById(prototypeModel);
		// To fix the missing created-at field, return the prototype from the repository
		return prototypeRepositoryPort.getPrototypeById(prototypeId);
	}

	private void validate(PrototypeModel prototypeModel) {
		ofNullable(prototypeModel.getName())
				.orElseThrow(IllegalAccessError::new);
		prototypeRepositoryPort.getPrototypeById(prototypeModel.getId())
				.orElseThrow(EntityNotFoundException::new);
	}

}
