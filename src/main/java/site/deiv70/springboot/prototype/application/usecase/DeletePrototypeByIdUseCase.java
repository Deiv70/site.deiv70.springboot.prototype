package site.deiv70.springboot.prototype.application.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;

import java.util.UUID;

@AllArgsConstructor
@Service
public class DeletePrototypeByIdUseCase {

	private PrototypeRepositoryPort prototypeRepositoryPort;

	public void deletePrototypeById(UUID id) {
		prototypeRepositoryPort.deletePrototypeById(id);
	}

}
