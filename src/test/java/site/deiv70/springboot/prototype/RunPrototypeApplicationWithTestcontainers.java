package site.deiv70.springboot.prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MariaDBContainer;

@TestConfiguration(proxyBeanMethods = false)
public class RunPrototypeApplicationWithTestcontainers {

	@Bean
	@RestartScope // Prevents the Bean from being Restarted by Spring Boot DevTools
	@ServiceConnection // Modern way to tell Spring Boot to retrieve Connection details and use them later
	MariaDBContainer<?> mariaDbContainer() {
		return new MariaDBContainer<>("mariadb:latest")
			.withInitScript("schema.sql");
	}

/*
	@DynamicPropertySource // Old way to tell Spring Boot connection details for later usage
	static void setDatasourceProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", jpaContainer::getJdbcUrl);
		registry.add("spring.datasource.username", jpaContainer::getUsername);
		registry.add("spring.datasource.password", jpaContainer::getPassword);
	}
*/

	public static void main(String[] args) {
		SpringApplication.from(PrototypeApplication::main).with(RunPrototypeApplicationWithTestcontainers.class).run(args);
	}

}
