package site.deiv70.springboot.prototype.infrastructure.secondary.persistence.jpa.adapter;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.PrototypeRepositoryPort;
import site.deiv70.springboot.prototype.infrastructure.secondary.mapper.jpa.PrototypeEntityMapperJPA;
import site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa.PrototypeEntityModelJPA;
import site.deiv70.springboot.prototype.infrastructure.secondary.persistence.jpa.PrototypeEntityJPARepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
@ConditionalOnProperty(prefix = "cop", name = "database-service", havingValue = "jpa", matchIfMissing = true)
@AllArgsConstructor
public class PrototypeRepositoryAdapterJPA implements PrototypeRepositoryPort {

	private PrototypeEntityJPARepository prototypeEntityJPARepository;
	private PrototypeEntityMapperJPA prototypeEntityMapperJPA;

	@Override
	public Optional<PrototypeModel> getPrototypeById(UUID id) {
		return prototypeEntityJPARepository.findById(id)
				.map(prototypeEntityMapperJPA::toPrototypeModel);
	}

	@Override
	public List<PrototypeModel> createPrototypes(List<PrototypeModel> prototypeModelList) {
		List<PrototypeEntityModelJPA> prototypeEntityModelJPAList = prototypeEntityMapperJPA.toPrototypeEntityModelJPAList(prototypeModelList);

		return prototypeEntityMapperJPA.toPrototypeModelList(
				prototypeEntityJPARepository.saveAll(prototypeEntityModelJPAList)
		);

	}

	@Override
	public Page<PrototypeModel> getAllPrototypes(Pageable pageable) {
		return prototypeEntityJPARepository.findAll(pageable)
			.map(prototypeEntityMapperJPA::toPrototypeModel);
	}

	@Override
	public Page<PrototypeModel> getPrototypesByName(String name, Pageable pageable) {
		return prototypeEntityJPARepository.findByNameContaining(name, pageable)
			.map(prototypeEntityMapperJPA::toPrototypeModel);
	}

	@Override
	public Optional<PrototypeModel> updatePrototypeById(PrototypeModel prototypeModel) {
		PrototypeEntityModelJPA prototypeEntityModelJPA = prototypeEntityMapperJPA.toPrototypeEntityModelJPA(prototypeModel);

		return Optional.ofNullable(
			prototypeEntityMapperJPA.toPrototypeModel(
				prototypeEntityJPARepository.save(prototypeEntityModelJPA)
			)
		);
	}

	@Override
	public void deletePrototypeById(UUID id) {
		prototypeEntityJPARepository.deleteById(id);
	}

}
