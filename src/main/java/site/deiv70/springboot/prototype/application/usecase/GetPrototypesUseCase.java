package site.deiv70.springboot.prototype.application.usecase;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;

@AllArgsConstructor
@Component
public class GetPrototypesUseCase {

    private PrototypeRepositoryPort prototypeRepositoryPort;

    public Page<PrototypeModel> getAllPrototypes(final Pageable pageable) {
        return prototypeRepositoryPort.getAllPrototypes(pageable);
    }

    public Page<PrototypeModel> getPrototypesByName(final String name, final Pageable pageable) {
        return prototypeRepositoryPort.getPrototypesByName(name, pageable);
    }

}
