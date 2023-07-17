package site.deiv70.springboot.prototype.application.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CreatePrototypesUseCase {

	private PrototypeRepositoryPort prototypeRepositoryPort;

	public List<PrototypeModel> createPrototypes(final List<PrototypeModel> prototypeModelList) {
		if (!prototypeModelList.iterator().hasNext()) {
			return null;
		}

		// Set Ids to random if not already set
		prototypeModelList.forEach(
				prototypeModel -> {
					if (prototypeModel.getId() == null) {
						prototypeModel.setId(UUID.randomUUID());
					}
					// Assign new Id if it already exists
					if (prototypeRepositoryPort.getPrototypeById(prototypeModel.getId()).isPresent()) {
						prototypeModel.setId(UUID.randomUUID());
					}
				});

		return prototypeRepositoryPort.createPrototypes(prototypeModelList);

	}

}
