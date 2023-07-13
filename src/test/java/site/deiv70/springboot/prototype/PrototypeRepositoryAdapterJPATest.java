package site.deiv70.springboot.prototype;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.infrastructure.secondary.persistence.jpa.adapter.PrototypeRepositoryAdapterJPA;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"cop.database-service=jpa"})
//@TestConfiguration(proxyBeanMethods = false)
public class PrototypeRepositoryAdapterJPATest extends AbstractTest {

	@Autowired
	private PrototypeRepositoryAdapterJPA prototypeRepositoryAdapterJPA;

	@Value("${spring.datasource.username}")
	private static String USERNAME;
	@Value("${spring.datasource.password}")
	private static String PASSWORD;

	// @ServiceConnection
	@Container
	private MariaDBContainer mariaDBContainer = new MariaDBContainer("mariadb:latest")
			.withUsername(USERNAME)
			.withPassword(PASSWORD)
			.withDatabaseName("test");

//	public static void main(String[] args) {
//		SpringApplication.from(PrototypeApplication::main).with(PrototypeRepositoryAdapterJPATest.class).run(args);
//	}

	@Test
	void testCreatePrototype() {
		PrototypeModel prototypeModel = easyRandom.nextObject(PrototypeModel.class);
		Iterable prototypeModelIterable = Arrays.asList(prototypeModel);

		Iterable<PrototypeModel> createdPrototypeModel = prototypeRepositoryAdapterJPA.createPrototypes(prototypeModelIterable);
		Assertions.assertEquals(prototypeModel.getName(), createdPrototypeModel.iterator().next().getName());
	}

}
