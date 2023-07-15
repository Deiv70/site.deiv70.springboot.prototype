package site.deiv70.springboot.prototype.infrastructure.secondary.persistence.jpa.adapter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.deiv70.springboot.prototype.common.IntegrationTestAbstractJPAContainer;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;

import java.util.Collections;
import java.util.List;

@SpringBootTest(properties = {"cop.database-service=jpa"})
class PrototypeRepositoryAdapterJPAIntegrationTest extends IntegrationTestAbstractJPAContainer {

	@Autowired
	private PrototypeRepositoryAdapterJPA prototypeRepositoryAdapterJPA;

	@BeforeEach
	public void setUp() {

	}

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
