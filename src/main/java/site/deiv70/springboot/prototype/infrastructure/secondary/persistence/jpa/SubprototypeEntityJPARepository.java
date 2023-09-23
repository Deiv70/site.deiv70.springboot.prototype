package site.deiv70.springboot.prototype.infrastructure.secondary.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa.SubprototypeEntityModelJPA;

import java.util.UUID;

@Repository // theoretically not needed: it is detected by Spring as it extends JpaRepository
public interface SubprototypeEntityJPARepository extends JpaRepository<SubprototypeEntityModelJPA, UUID> {

	Page<SubprototypeEntityModelJPA> findByNameContaining(String name, Pageable pageable);

}
