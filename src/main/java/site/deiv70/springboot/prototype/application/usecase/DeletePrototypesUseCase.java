package site.deiv70.springboot.prototype.application.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.IdRequestDtoModel;

import java.util.List;

@AllArgsConstructor
@Service
public class DeletePrototypesUseCase {

	private PrototypeRepositoryPort prototypeRepositoryPort;

	public void deletePrototypesById(final List<IdRequestDtoModel> idRequestDtoModelList) {
		idRequestDtoModelList.forEach(idRequestDtoModel -> {
			prototypeRepositoryPort.deletePrototypeById(idRequestDtoModel.getId());
		});
	}
}
