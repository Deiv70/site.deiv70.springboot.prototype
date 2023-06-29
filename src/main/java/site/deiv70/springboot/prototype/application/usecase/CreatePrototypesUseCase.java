package site.deiv70.springboot.prototype.application.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;

@AllArgsConstructor
@Component
public class CreatePrototypesUseCase {

    private PrototypeRepositoryPort prototypeRepositoryPort;

    public Iterable<PrototypeModel> createPrototypes(final Iterable<PrototypeModel> prototypeModelIterable) {
        if ( ! prototypeModelIterable.iterator().hasNext() ) {
            return null;
        }

        // Set Ids to null to avoid overwriting existing prototypes
        prototypeModelIterable.forEach(prototypeModel -> prototypeModel.setId(null));

        return prototypeRepositoryPort.createPrototypes(prototypeModelIterable);

    }

}
