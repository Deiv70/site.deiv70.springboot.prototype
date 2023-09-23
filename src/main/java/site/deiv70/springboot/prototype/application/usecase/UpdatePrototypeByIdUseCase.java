package site.deiv70.springboot.prototype.application.usecase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;
import site.deiv70.springboot.prototype.infrastructure.primary.exception.ApiRequestException;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UpdatePrototypeByIdUseCase {

	private final PrototypeRepositoryPort prototypeRepositoryPort;

	public PrototypeModel execute(@Valid final PrototypeModel prototypeModel) {
		// Update the prototype
		return prototypeRepositoryPort.updatePrototypeById(prototypeModel)
			.orElseThrow(() -> new ApiRequestException(
				HttpStatus.NOT_MODIFIED,
				Collections.singletonList("Prototype ID: '" + prototypeModel.getId() + "' update failed")
			));
		// To fix the missing created-at field, return the prototype from the repository
		/*
		return prototypeRepositoryPort.getPrototypeById(prototypeModel.getId())
			.orElseThrow(() -> new ApiRequestException(
				HttpStatus.NOT_FOUND,
				Collections.singletonList("Prototype ID: '" + prototypeModel.getId() + "' not found after update"))
			);
		*/
	}
}
