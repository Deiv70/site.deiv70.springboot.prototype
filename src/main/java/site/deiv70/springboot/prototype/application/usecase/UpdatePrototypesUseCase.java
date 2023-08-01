package site.deiv70.springboot.prototype.application.usecase;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@AllArgsConstructor
@Service
public class UpdatePrototypesUseCase {

	private PrototypeRepositoryPort prototypeRepositoryPort;

	public List<PrototypeModel> updatePrototypes(final List<PrototypeModel> prototypeModelList) {
		List<PrototypeModel> updatedPrototypesList = new ArrayList<>();

		prototypeModelList.forEach(prototypeModel -> {
			validate(prototypeModel);
			updatedPrototypesList.add(
					prototypeRepositoryPort.updatePrototypeById(prototypeModel).get());
		});

		return updatedPrototypesList;
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
