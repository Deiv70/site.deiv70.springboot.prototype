package site.deiv70.springboot.prototype.application.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;
import site.deiv70.springboot.prototype.infrastructure.primary.exception.ApiRequestException;

import java.util.UUID;

@AllArgsConstructor
@Service
public class DeletePrototypeByIdUseCase {

	private PrototypeRepositoryPort prototypeRepositoryPort;

	public void execute(final UUID id) {
		validate(id);
		prototypeRepositoryPort.deletePrototypeById(id);
	}

	private void validate(final UUID id) {
		prototypeRepositoryPort.getPrototypeById(id)
				.orElseThrow(() ->
					new ApiRequestException(ApiRequestException.Type.ENTITY_NOT_FOUND_EXCEPTION,
						"Prototype not found by It's ID"));
	}

}
