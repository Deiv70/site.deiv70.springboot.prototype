package site.deiv70.springboot.prototype.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.SubprototypeRepositoryPort;
import site.deiv70.springboot.prototype.infrastructure.primary.exception.ApiRequestException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CreatePrototypesUseCase {

	private final PrototypeRepositoryPort prototypeRepositoryPort;
	private final SubprototypeRepositoryPort subprototypeRepositoryPort;

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
						throw new ApiRequestException(
							HttpStatus.BAD_REQUEST,
                            Collections.singletonList("Prototype's ID already exists"));
						//prototypeModel.setId(UUID.randomUUID());
					}
					prototypeModel.getSubprototypeModelList().forEach(
						subprototypeModel -> subprototypeRepositoryPort.getSubprototypeById(subprototypeModel.getId())
								.ifPresentOrElse(
										subprototypeModel1 -> {
											throw new ApiRequestException(HttpStatus.NOT_FOUND,
                                                    Collections.singletonList("Subprototype's ID NOT Found"));
										},
										() -> subprototypeModel.setId(UUID.randomUUID())));
				});

		return prototypeRepositoryPort.createPrototypes(prototypeModelList);

	}

}
