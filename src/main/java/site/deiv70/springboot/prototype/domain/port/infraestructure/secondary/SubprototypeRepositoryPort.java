package site.deiv70.springboot.prototype.domain.port.infraestructure.secondary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.model.entity.SubprototypeModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubprototypeRepositoryPort {

    Optional<SubprototypeModel> getSubprototypeById(UUID id);

	Optional<SubprototypeModel> updateSubprototypeById(SubprototypeModel subprototypeModel);

	void deleteSubprototypeById(UUID id);

    List<SubprototypeModel> createSubprototypes(List<SubprototypeModel> subprototypeModelList);

    Page<SubprototypeModel> getAllSubprototypes(Pageable pageable);

    Page<SubprototypeModel> getSubprototypesByName(String name, Pageable pageable);

}
