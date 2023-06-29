package site.deiv70.springboot.prototype.infrastructure.secondary.persistence.jpa.adapter;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;
import site.deiv70.springboot.prototype.infrastructure.secondary.mapper.jpa.PrototypeEntityMapperJPA;
import site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa.PrototypeEntityModelJPA;
import site.deiv70.springboot.prototype.infrastructure.secondary.persistence.jpa.PrototypeEntityJPACrudRepository;

import java.util.Optional;
import java.util.UUID;


@AllArgsConstructor
@Repository
@ConditionalOnProperty(prefix = "cop", name = "database-service", havingValue = "jpa", matchIfMissing = true)
public class PrototypeRepositoryAdapterJPA implements PrototypeRepositoryPort {

	private PrototypeEntityJPACrudRepository prototypeEntityJPACrudRepository;
	private PrototypeEntityMapperJPA prototypeEntityMapperJPA;

	@Override
	public Optional<PrototypeModel> getPrototypeById(UUID id) {
		return prototypeEntityJPACrudRepository.findById(id)
				.map(prototypeEntityMapperJPA::toPrototypeModel);
	}

	@Override
	public Iterable<PrototypeModel> createPrototypes(Iterable<PrototypeModel> prototypeModelIterable) {
		Iterable<PrototypeEntityModelJPA> prototypeEntityModelJPAIterable = prototypeEntityMapperJPA.toPrototypeEntityModelJPAIterable(prototypeModelIterable);

		return prototypeEntityMapperJPA.toPrototypeModelIterable(
				prototypeEntityJPACrudRepository.saveAll(prototypeEntityModelJPAIterable)
		);

	}

	@Override
	public Iterable<PrototypeModel> getAllPrototypes() {
		return prototypeEntityMapperJPA.toPrototypeModelIterable(
				prototypeEntityJPACrudRepository.findAll()
		);
	}

	@Override
	public Iterable<PrototypeModel> getPrototypesByName(String name) {
		return prototypeEntityMapperJPA.toPrototypeModelIterable(
				prototypeEntityJPACrudRepository.findByName(name)
		);
	}

}
