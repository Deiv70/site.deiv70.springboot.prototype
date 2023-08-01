package site.deiv70.springboot.prototype.infrastructure.secondary.persistence.jpa.adapter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import site.deiv70.springboot.prototype.common.TestAbstract;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.infrastructure.secondary.mapper.jpa.PrototypeEntityMapperJPA;
import site.deiv70.springboot.prototype.infrastructure.secondary.mapper.jpa.PrototypeEntityMapperJPAImpl;
import site.deiv70.springboot.prototype.infrastructure.secondary.persistence.jpa.PrototypeEntityJPARepository;

import java.util.Collections;
import java.util.List;

@DataJpaTest
class PrototypeRepositoryAdapterJPAIntegrationTest extends TestAbstract {
	private final PrototypeEntityMapperJPA prototypeEntityMapperJPA = new PrototypeEntityMapperJPAImpl();
	private PrototypeRepositoryAdapterJPA prototypeRepositoryAdapterJPA;

	@Autowired
	private PrototypeEntityJPARepository prototypeEntityJPARepository;

	@BeforeEach
	void setUp() {
		prototypeRepositoryAdapterJPA = new PrototypeRepositoryAdapterJPA(prototypeEntityJPARepository, prototypeEntityMapperJPA);
	}

	@Test
	void testCreatePrototype() {
		PrototypeModel prototypeModel = easyRandom.nextObject(PrototypeModel.class);
		// Make an Iterable from a single object
		List<PrototypeModel> prototypeModelList = Collections.singletonList(prototypeModel);

		Iterable<PrototypeModel> createdPrototypeModel = prototypeRepositoryAdapterJPA.createPrototypes(prototypeModelList);
		Assertions.assertEquals(prototypeModel.getName(), createdPrototypeModel.iterator().next().getName());
	}

}
