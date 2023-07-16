package site.deiv70.springboot.prototype.infrastructure.primary.controller.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import site.deiv70.springboot.prototype.application.usecase.CreatePrototypesUseCase;
import site.deiv70.springboot.prototype.application.usecase.DeletePrototypeByIdUseCase;
import site.deiv70.springboot.prototype.application.usecase.DeletePrototypesUseCase;
import site.deiv70.springboot.prototype.application.usecase.GetPrototypeByIdUseCase;
import site.deiv70.springboot.prototype.application.usecase.GetPrototypesUseCase;
import site.deiv70.springboot.prototype.application.usecase.UpdatePrototypeByIdUseCase;
import site.deiv70.springboot.prototype.application.usecase.UpdatePrototypesUseCase;
import site.deiv70.springboot.prototype.common.TestAbstract;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.infrastructure.primary.mapper.PrototypeDtoMapper;
import site.deiv70.springboot.prototype.infrastructure.primary.mapper.PrototypeDtoMapperImpl;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PrototypeApiRestControllerTest extends TestAbstract {

	private PrototypeApiRestController prototypeApiRestController;
	private final PrototypeDtoMapper prototypeDtoMapper = new PrototypeDtoMapperImpl();

	@Mock
	private GetPrototypeByIdUseCase getPrototypeByIdUseCase;
	@Mock
	private UpdatePrototypeByIdUseCase updatePrototypeByIdUseCase;
	@Mock
	private DeletePrototypeByIdUseCase deletePrototypeByIdUseCase;

	@Mock
	private GetPrototypesUseCase getPrototypesUseCase;
	@Mock
	private CreatePrototypesUseCase createPrototypesUseCase;
	@Mock
	private UpdatePrototypesUseCase updatePrototypesUseCase;
	@Mock
	private DeletePrototypesUseCase deletePrototypesUseCase;

	@BeforeEach
	void setUp() {
		prototypeApiRestController = new PrototypeApiRestController(
				prototypeDtoMapper,
				getPrototypeByIdUseCase,
				updatePrototypeByIdUseCase,
				deletePrototypeByIdUseCase,
				getPrototypesUseCase,
				createPrototypesUseCase,
				updatePrototypesUseCase,
				deletePrototypesUseCase
		);
	}

	@Test
	void getPrototypeById_return_OK() {
		// Given
		PrototypeModel prototypeModel = easyRandom.nextObject(PrototypeModel.class);
		UUID uuid = UUID.randomUUID();
		prototypeModel.setId(uuid);

		// When
		Mockito.when(getPrototypeByIdUseCase.getPrototypeById(uuid))
			.thenReturn(Optional.of(prototypeModel));

		// Then
		Assertions.assertEquals(
			prototypeModel.getName(),
			Objects.requireNonNull(
				prototypeApiRestController.getPrototypeById(prototypeModel.getId())
					.getBody())
			.getName());
	}

	@Test
	void getPrototypeById_return_NotFound() {
		// Given
		PrototypeModel prototypeModel = easyRandom.nextObject(PrototypeModel.class);
		UUID uuid = UUID.randomUUID();
		prototypeModel.setId(uuid);

		// When
		Mockito.when(getPrototypeByIdUseCase.getPrototypeById(uuid))
			.thenReturn(Optional.empty());

		// Then
		Assertions.assertEquals(
			ResponseEntity.notFound().build().getStatusCode(),
			prototypeApiRestController.getPrototypeById(prototypeModel.getId())
				.getStatusCode());
	}

	@Test
	void getPrototypeById_return_BadRequest() {
		// Then
		Assertions.assertEquals(
			ResponseEntity.badRequest().build().getStatusCode(),
			prototypeApiRestController.getPrototypeById(null).getStatusCode()
		);
	}

	@Test
	void updatePrototypeById() {
	}

	@Test
	void deletePrototypeById() {
	}

	@Test
	void getPrototypes() {
	}

	@Test
	void createPrototypes() {
	}

}
