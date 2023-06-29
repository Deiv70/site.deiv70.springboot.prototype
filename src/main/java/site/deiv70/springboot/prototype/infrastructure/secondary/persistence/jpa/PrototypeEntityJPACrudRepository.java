package site.deiv70.springboot.prototype.infrastructure.secondary.persistence.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa.PrototypeEntityModelJPA;

import java.util.UUID;

@Repository
public interface PrototypeEntityJPACrudRepository extends CrudRepository<PrototypeEntityModelJPA, UUID> {

	Iterable<PrototypeEntityModelJPA> findByName(String name);

}
