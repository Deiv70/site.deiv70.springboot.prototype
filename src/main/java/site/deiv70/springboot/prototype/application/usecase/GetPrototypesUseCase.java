package site.deiv70.springboot.prototype.application.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;

@AllArgsConstructor
@Component
public class GetPrototypesUseCase {

    private PrototypeRepositoryPort prototypeRepositoryPort;

    public Iterable<PrototypeModel> getAllPrototypes() {
        return prototypeRepositoryPort.getAllPrototypes();
    }

    public Iterable<PrototypeModel> getPrototypesByName(final String name) {
        return prototypeRepositoryPort.getPrototypesByName(name);
    }

}
