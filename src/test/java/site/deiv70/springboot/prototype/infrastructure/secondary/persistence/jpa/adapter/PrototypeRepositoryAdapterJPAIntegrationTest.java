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

//	@Autowired private DataSource dataSource;
//	@Autowired private JdbcTemplate jdbcTemplate;
//	@Autowired private EntityManager entityManager;

//	@Test
//	void injectedComponentsAreNotNull(){
//		assertNotNull(dataSource);
//		assertNotNull(jdbcTemplate);
//		assertNotNull(entityManager);
//		assertNotNull(prototypeRepositoryAdapterJPA);
//	}

//	@Test
//	void test_restartable_db() {
//		Assertions.assertTrue(jpaContainer.isRunning());
//		Assertions.assertEquals("testuser", jpaContainer.getUsername());
//		Assertions.assertEquals("testpwd", jpaContainer.getPassword());
//		Assertions.assertEquals("testdb", jpaContainer.getDatabaseName());
//		Assertions.assertEquals("jdbc:mariadb://localhost:" + jpaContainer.getMappedPort(3306) + "/testdb", jpaContainer.getJdbcUrl());
//	}

	@Test
	void testCreatePrototype() {
		PrototypeModel prototypeModel = easyRandom.nextObject(PrototypeModel.class);
		// Make an Iterable from a single object
		List<PrototypeModel> prototypeModelList = Collections.singletonList(prototypeModel);

		Iterable<PrototypeModel> createdPrototypeModel = prototypeRepositoryAdapterJPA.createPrototypes(prototypeModelList);
		Assertions.assertEquals(prototypeModel.getName(), createdPrototypeModel.iterator().next().getName());
	}

}
