package site.deiv70.springboot.prototype.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;
import site.deiv70.springboot.prototype.infrastructure.primary.exception.ApiRequestException;

import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetPrototypeByIdUseCase {

    private final PrototypeRepositoryPort prototypeRepositoryPort;

    public PrototypeModel execute(final UUID id) {
        return prototypeRepositoryPort.getPrototypeById(id)
			.orElseThrow(() -> new ApiRequestException(
				HttpStatus.NOT_FOUND,
				Collections.singletonList("Prototype ID: '" + id + "' not found")
			));
    }

}
