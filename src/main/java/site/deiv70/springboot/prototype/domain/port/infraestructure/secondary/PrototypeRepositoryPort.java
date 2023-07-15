package site.deiv70.springboot.prototype.domain.port.infraestructure.secondary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PrototypeRepositoryPort {

    Optional<PrototypeModel> getPrototypeById(UUID id);

	Optional<PrototypeModel> updatePrototypeById(PrototypeModel prototypeModel);

	void deletePrototypeById(UUID id);

    List<PrototypeModel> createPrototypes(List<PrototypeModel> prototypeModelList);

    Page<PrototypeModel> getAllPrototypes(Pageable pageable);

    Page<PrototypeModel> getPrototypesByName(String name, Pageable pageable);

}
