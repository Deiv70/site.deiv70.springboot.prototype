package site.deiv70.springboot.prototype.infrastructure.secondary.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa.PrototypeEntityModelJPA;

import java.util.UUID;

@Repository
public interface PrototypeEntityJPARepository extends JpaRepository<PrototypeEntityModelJPA, UUID> {

	Page<PrototypeEntityModelJPA> findByName(String name, Pageable pageable);

}
