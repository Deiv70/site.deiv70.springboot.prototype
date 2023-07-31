package site.deiv70.springboot.prototype.common;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = IntegrationTestAbstractJPAContainer.DataSourceInitializer.class)
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

	public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
				applicationContext,
				"spring.test.database.replace=none",
				"spring.datasource.url=" + jpaContainer.getJdbcUrl(),
				"spring.datasource.username=" + jpaContainer.getUsername(),
				"spring.datasource.password=" + jpaContainer.getPassword()
			);
		}
	}

/*
	@DynamicPropertySource // Old way to tell Spring Boot connection details for later usage
	static void setDatasourceProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", jpaContainer::getJdbcUrl);
		registry.add("spring.datasource.username", jpaContainer::getUsername);
		registry.add("spring.datasource.password", jpaContainer::getPassword);
	}
*/

}
