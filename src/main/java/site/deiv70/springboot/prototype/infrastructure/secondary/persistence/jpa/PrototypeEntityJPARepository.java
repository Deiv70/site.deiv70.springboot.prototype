package site.deiv70.springboot.prototype.infrastructure.secondary.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa.PrototypeEntityModelJPA;

import java.util.UUID;

// @Repository not needed: it is detected by Spring as it extends JpaRepository
public interface PrototypeEntityJPARepository extends JpaRepository<PrototypeEntityModelJPA, UUID> {

	Page<PrototypeEntityModelJPA> findByNameContaining(String name, Pageable pageable);

}
