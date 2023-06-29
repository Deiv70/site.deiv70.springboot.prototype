package site.deiv70.springboot.prototype.application.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class GetPrototypeByIdUseCase {

    private PrototypeRepositoryPort prototypeRepositoryPort;

    public Optional<PrototypeModel> getPrototypeById(final UUID id) {
        return prototypeRepositoryPort.getPrototypeById(id);
    }

}
