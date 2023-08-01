package site.deiv70.springboot.prototype.application.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;
import site.deiv70.springboot.prototype.infrastructure.primary.exception.ApiRequestException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CreatePrototypesUseCase {

	private PrototypeRepositoryPort prototypeRepositoryPort;

	public List<PrototypeModel> execute(final List<PrototypeModel> prototypeModelList) {
		if (!prototypeModelList.iterator().hasNext()) {
			return Collections.emptyList();
		}

		// Set Ids to random if not already set
		prototypeModelList.forEach(
				prototypeModel -> {
					if (prototypeModel.getId() == null) {
						prototypeModel.setId(UUID.randomUUID());
					}
					// Assign new Id if it already exists
					if (prototypeRepositoryPort.getPrototypeById(prototypeModel.getId()).isPresent()) {
						throw new ApiRequestException(ApiRequestException.Type.ID_ALREADY_USED_EXCEPTION,
								"Prototype's ID already exists");
						//prototypeModel.setId(UUID.randomUUID());
					}
				});

		return prototypeRepositoryPort.createPrototypes(prototypeModelList);

	}

}
