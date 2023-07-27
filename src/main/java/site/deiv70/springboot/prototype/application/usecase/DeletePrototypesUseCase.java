package site.deiv70.springboot.prototype.application.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.IdDtoModel;

import java.util.List;

@AllArgsConstructor
@Service
public class DeletePrototypesUseCase {

	private PrototypeRepositoryPort prototypeRepositoryPort;

	public void deletePrototypesById(final List<IdDtoModel> idDtoModelList) {
		idDtoModelList.forEach(idRequestDtoModel -> {
			prototypeRepositoryPort.deletePrototypeById(idRequestDtoModel.getId());
		});
	}
}
