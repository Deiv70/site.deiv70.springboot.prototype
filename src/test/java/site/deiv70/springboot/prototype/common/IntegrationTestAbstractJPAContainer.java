package site.deiv70.springboot.prototype.common;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
@Testcontainers
public abstract class IntegrationTestAbstractJPAContainer extends TestAbstract {

	// Container will be started before and stopped after each test method
	@Container
	@ServiceConnection // Modern way to tell Spring Boot 3.1.X to retrieve Connection details for later usage
	public static MariaDBContainer<?> jpaContainer = new MariaDBContainer<>("mariadb:latest")
		//TODO: Try to set the following properties in application.yaml and retrieve them from there
		.withUsername("testuser")
		.withPassword("testpwd")
		.withDatabaseName("testdb")
		.withInitScript("schema.sql");

/*
	@DynamicPropertySource // Old way to tell Spring Boot connection details for later usage
	static void setDatasourceProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", jpaContainer::getJdbcUrl);
		registry.add("spring.datasource.username", jpaContainer::getUsername);
		registry.add("spring.datasource.password", jpaContainer::getPassword);
	}
*/

}
