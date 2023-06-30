package site.deiv70.springboot.prototype.domain.port.infraestructure.secondary;

import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;

import java.util.Optional;
import java.util.UUID;

public interface PrototypeRepositoryPort {

    Optional<PrototypeModel> getPrototypeById(UUID id);

    Iterable<PrototypeModel> createPrototypes(Iterable<PrototypeModel> prototypeModelList);

    Iterable<PrototypeModel> getAllPrototypes();

    Iterable<PrototypeModel> getPrototypesByName(String name);

    Optional<PrototypeModel> updatePrototypeById(PrototypeModel prototypeModel);

	void deletePrototypeById(UUID id);

}
