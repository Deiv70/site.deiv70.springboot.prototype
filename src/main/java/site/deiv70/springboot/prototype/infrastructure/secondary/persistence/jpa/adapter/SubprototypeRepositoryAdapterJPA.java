package site.deiv70.springboot.prototype.infrastructure.secondary.persistence.jpa.adapter;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import site.deiv70.springboot.prototype.domain.model.entity.SubprototypeModel;
import site.deiv70.springboot.prototype.domain.port.infraestructure.secondary.SubprototypeRepositoryPort;
import site.deiv70.springboot.prototype.infrastructure.secondary.mapper.jpa.SubprototypeEntityMapperJPA;
import site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa.SubprototypeEntityModelJPA;
import site.deiv70.springboot.prototype.infrastructure.secondary.persistence.jpa.SubprototypeEntityJPARepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@ConditionalOnProperty(prefix = "cop", name = "database-service", havingValue = "jpa", matchIfMissing = true)
@RequiredArgsConstructor
public class SubprototypeRepositoryAdapterJPA implements SubprototypeRepositoryPort {

	private final SubprototypeEntityJPARepository subprototypeEntityJPARepository;
	private final SubprototypeEntityMapperJPA subprototypeEntityMapperJPA;

	@Override
	public Optional<SubprototypeModel> getSubprototypeById(UUID id) {
		return subprototypeEntityJPARepository.findById(id)
			.map(subprototypeEntityMapperJPA::toSubprototypeModel);
	}

	@Override
	public List<SubprototypeModel> createSubprototypes(List<SubprototypeModel> prototypeModelList) {
		List<SubprototypeEntityModelJPA> prototypeEntityModelJPAList = subprototypeEntityMapperJPA.toSubprototypeEntityModelJPAList(prototypeModelList);

		return subprototypeEntityMapperJPA.toSubprototypeModelList(
			subprototypeEntityJPARepository.saveAll(prototypeEntityModelJPAList)
		);

	}

	@Override
	public Page<SubprototypeModel> getAllSubprototypes(Pageable pageable) {
		return subprototypeEntityJPARepository.findAll(pageable)
			.map(subprototypeEntityMapperJPA::toSubprototypeModel);
	}

	@Override
	public Page<SubprototypeModel> getSubprototypesByName(String name, Pageable pageable) {
		return subprototypeEntityJPARepository.findByNameContaining(name, pageable)
			.map(subprototypeEntityMapperJPA::toSubprototypeModel);
	}

	@Override
	public Optional<SubprototypeModel> updateSubprototypeById(SubprototypeModel prototypeModel) {
		SubprototypeEntityModelJPA prototypeEntityModelJPA = subprototypeEntityMapperJPA.toSubprototypeEntityModelJPA(prototypeModel);

		return Optional.ofNullable(
			subprototypeEntityMapperJPA.toSubprototypeModel(
				subprototypeEntityJPARepository.save(prototypeEntityModelJPA)
			)
		);
	}

	@Override
	public void deleteSubprototypeById(UUID id) {
		subprototypeEntityJPARepository.deleteById(id);
	}

}
