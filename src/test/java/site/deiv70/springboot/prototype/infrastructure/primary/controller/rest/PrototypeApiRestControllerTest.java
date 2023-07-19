package site.deiv70.springboot.prototype.infrastructure.primary.controller.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import site.deiv70.springboot.prototype.infrastructure.primary.dto.IdRequestDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypeDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypeUpdateRequestDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypesPaginatedResponseDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.mapper.PrototypeDtoMapper;
import site.deiv70.springboot.prototype.infrastructure.primary.mapper.PrototypeDtoMapperImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
				deletePrototypesUseCase);
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
		ResponseEntity<PrototypeDtoModel> responseEntity = prototypeApiRestController.getPrototypeById(uuid);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assertions.assertEquals(prototypeModel.getName(), Objects.requireNonNull(responseEntity.getBody()).getName());
	}

	@Test
	void getPrototypeById_return_NotFound() {
		// Given
		UUID uuid = UUID.randomUUID();

		// When
		Mockito.when(getPrototypeByIdUseCase.getPrototypeById(uuid))
				.thenReturn(Optional.empty());

		// Then
		ResponseEntity<PrototypeDtoModel> responseEntity = prototypeApiRestController.getPrototypeById(uuid);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	void getPrototypeById_return_BadRequest() {
		// Given
		UUID uuid = null;

		// When

		// Then
		ResponseEntity<PrototypeDtoModel> responseEntity = prototypeApiRestController.getPrototypeById(uuid);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void updatePrototypeById_return_OK() {	// TODO: Check why this test fails
		// Given
		PrototypeModel prototypeModel = easyRandom.nextObject(PrototypeModel.class);
		UUID uuid = UUID.randomUUID();
		prototypeModel.setId(uuid);

		PrototypeUpdateRequestDtoModel prototypeUpdateRequestDtoModel =
				prototypeDtoMapper.toPrototypeUpdateRequestDtoModel(prototypeModel);

		// When
		Mockito.when(updatePrototypeByIdUseCase.updatePrototypeById(uuid, prototypeModel))
				.thenReturn(Optional.of(prototypeModel));

		// Then
		ResponseEntity<PrototypeDtoModel> responseEntity =
				prototypeApiRestController.updatePrototypeById(uuid, prototypeUpdateRequestDtoModel);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		//Assertions.assertEquals(prototypeModel.getName(), Objects.requireNonNull(responseEntity.getBody()).getName());
	}

	@Test
	void updatePrototypeById_return_NotFound() {
		// Given
		UUID uuid = UUID.randomUUID();
		PrototypeUpdateRequestDtoModel prototypeUpdateRequestDtoModel =
				easyRandom.nextObject(PrototypeUpdateRequestDtoModel.class);

		// When
		Mockito.when(updatePrototypeByIdUseCase.updatePrototypeById(uuid,
				prototypeDtoMapper.updatedToPrototypeModel(prototypeUpdateRequestDtoModel)))
				.thenReturn(Optional.empty());

		// Then
		ResponseEntity<PrototypeDtoModel> responseEntity =
				prototypeApiRestController.updatePrototypeById(uuid, prototypeUpdateRequestDtoModel);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	void updatePrototypeById_return_BadRequest() {
		// Given
		UUID uuid = null;
		PrototypeUpdateRequestDtoModel prototypeUpdateRequestDtoModel = null;

		// When

		// Then
		ResponseEntity<PrototypeDtoModel> responseEntity =
				prototypeApiRestController.updatePrototypeById(uuid, prototypeUpdateRequestDtoModel);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void deletePrototypeById_return_OK() {
		// Given
		UUID uuid = UUID.randomUUID();

		// When

		// Then
		ResponseEntity<Void> responseEntity = prototypeApiRestController.deletePrototypeById(uuid);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void deletePrototypeById_return_BadRequest() {
		// Given
		UUID uuid = null;

		// When

		// Then
		ResponseEntity<Void> responseEntity = prototypeApiRestController.deletePrototypeById(uuid);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void getPrototypes_return_OK() {
		// Given
		List<PrototypeModel> prototypeModelList =
				easyRandom.objects(PrototypeModel.class, 5).collect(Collectors.toList());
		Pageable pageable = PageRequest.of(0, 10);
		PageImpl<PrototypeModel> prototypeModelPage =
				new PageImpl<>(prototypeModelList, pageable, prototypeModelList.size());

		// When
		Mockito.when(getPrototypesUseCase.getAllPrototypes(pageable))
				.thenReturn(prototypeModelPage);

		// Then
		ResponseEntity<PrototypesPaginatedResponseDtoModel> responseEntity =
				prototypeApiRestController.getPrototypes(null, pageable);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assertions.assertEquals(prototypeModelList.size(),
				Objects.requireNonNull(responseEntity.getBody()).getContent().size());
	}

	@Test
	void getPrototypes_return_OK_with_name() {
		// Given
		List<PrototypeModel> prototypeModelList =
				easyRandom.objects(PrototypeModel.class, 5).collect(Collectors.toList());
		Pageable pageable = PageRequest.of(0, 10);
		PageImpl<PrototypeModel> prototypeModelPage =
				new PageImpl<>(prototypeModelList, pageable, prototypeModelList.size());
		String name = "test";

		// When
		Mockito.when(getPrototypesUseCase.getPrototypesByName(name, pageable))
				.thenReturn(prototypeModelPage);

		// Then
		ResponseEntity<PrototypesPaginatedResponseDtoModel> responseEntity =
				prototypeApiRestController.getPrototypes(name, pageable);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assertions.assertEquals(prototypeModelList.size(),
				Objects.requireNonNull(responseEntity.getBody()).getContent().size());
	}

	@Test
	void createPrototypes_return_OK() {
		// Given
		List<PrototypeDtoModel> prototypeDtoModelList =
				easyRandom.objects(PrototypeDtoModel.class, 5).collect(Collectors.toList());
		List<PrototypeModel> prototypeModelList = prototypeDtoMapper.toPrototypeModelList(prototypeDtoModelList);

		// When
		Mockito.when(createPrototypesUseCase.createPrototypes(prototypeModelList))
				.thenReturn(prototypeModelList);

		// Then
		ResponseEntity<List<PrototypeDtoModel>> responseEntity =
				prototypeApiRestController.createPrototypes(prototypeDtoModelList);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assertions.assertEquals(prototypeDtoModelList.size(), Objects.requireNonNull(responseEntity.getBody()).size());
	}

	@Test
	void createPrototypes_return_BadRequest() {
		// Given
		List<PrototypeDtoModel> prototypeDtoModelList = new ArrayList<>();

		// When

		// Then
		ResponseEntity<List<PrototypeDtoModel>> responseEntity =
				prototypeApiRestController.createPrototypes(prototypeDtoModelList);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void deletePrototypes_return_OK() {
		// Given
		List<IdRequestDtoModel> idRequestDtoModelList =
				easyRandom.objects(IdRequestDtoModel.class, 5).collect(Collectors.toList());

		// When

		// Then
		ResponseEntity<Void> responseEntity = prototypeApiRestController.deletePrototypes(idRequestDtoModelList);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void deletePrototypes_return_BadRequest() {
		// Given
		List<IdRequestDtoModel> idRequestDtoModelList = new ArrayList<>();

		// When

		// Then
		ResponseEntity<Void> responseEntity = prototypeApiRestController.deletePrototypes(idRequestDtoModelList);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void updatePrototypes_return_OK() {
		// Given
		List<PrototypeDtoModel> prototypeDtoModelList =
				easyRandom.objects(PrototypeDtoModel.class, 5).collect(Collectors.toList());
		List<PrototypeModel> prototypeModelList = prototypeDtoMapper.toPrototypeModelList(prototypeDtoModelList);

		// When
		Mockito.when(updatePrototypesUseCase.updatePrototypes(prototypeModelList))
				.thenReturn(prototypeModelList);

		// Then
		ResponseEntity<List<PrototypeDtoModel>> responseEntity =
				prototypeApiRestController.updatePrototypes(prototypeDtoModelList);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assertions.assertEquals(prototypeDtoModelList.size(), Objects.requireNonNull(responseEntity.getBody()).size());
	}

	@Test
	void updatePrototypes_return_BadRequest() {
		// Given
		List<PrototypeDtoModel> prototypeDtoModelList = new ArrayList<>();

		// When

		// Then
		ResponseEntity<List<PrototypeDtoModel>> responseEntity =
				prototypeApiRestController.updatePrototypes(prototypeDtoModelList);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

}
