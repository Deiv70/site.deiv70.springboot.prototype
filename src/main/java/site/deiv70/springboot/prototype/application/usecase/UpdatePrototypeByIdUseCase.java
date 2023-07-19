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

	// TODO: Check why this is not returning the created-at field
	public Optional<PrototypeModel> updatePrototypeById(final UUID prototypeId, final PrototypeModel prototypeModel) {
		prototypeModel.setId(prototypeId);
		validate(prototypeModel);
		return prototypeRepositoryPort.updatePrototypeById(prototypeModel);
	}

	private void validate(PrototypeModel prototypeModel) {
		ofNullable(prototypeModel.getName())
				.orElseThrow(IllegalAccessError::new);
		prototypeRepositoryPort.getPrototypeById(prototypeModel.getId())
				.orElseThrow(EntityNotFoundException::new);
	}

}
