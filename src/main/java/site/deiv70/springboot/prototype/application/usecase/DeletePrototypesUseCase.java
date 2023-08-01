package site.deiv70.springboot.prototype.application.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.IdDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.exception.ApiRequestException;

import java.util.List;

@AllArgsConstructor
@Service
public class DeletePrototypesUseCase {

	private PrototypeRepositoryPort prototypeRepositoryPort;

	public void execute(final List<IdDtoModel> idDtoModelList) {
		idDtoModelList.forEach(idRequestDtoModel -> {
			if (prototypeRepositoryPort.getPrototypeById(idRequestDtoModel.getId()).isPresent()) {
				prototypeRepositoryPort.deletePrototypeById(idRequestDtoModel.getId());
			} else {
				throw new ApiRequestException(ApiRequestException.Type.ENTITY_NOT_FOUND_EXCEPTION,
					"Prototype not found by It's ID");

			}
		});
	}

}
