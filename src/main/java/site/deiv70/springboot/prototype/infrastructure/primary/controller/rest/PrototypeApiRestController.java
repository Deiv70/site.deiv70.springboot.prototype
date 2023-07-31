package site.deiv70.springboot.prototype.infrastructure.primary.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.deiv70.springboot.prototype.application.usecase.CreatePrototypesUseCase;
import site.deiv70.springboot.prototype.application.usecase.DeletePrototypeByIdUseCase;
import site.deiv70.springboot.prototype.application.usecase.DeletePrototypesUseCase;
import site.deiv70.springboot.prototype.application.usecase.GetPrototypeByIdUseCase;
import site.deiv70.springboot.prototype.application.usecase.GetPrototypesUseCase;
import site.deiv70.springboot.prototype.application.usecase.UpdatePrototypeByIdUseCase;
import site.deiv70.springboot.prototype.application.usecase.UpdatePrototypesUseCase;
import site.deiv70.springboot.prototype.infrastructure.primary.api.PrototypeApi;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.CriteriaDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.IdDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypeDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypeUpdateRequestDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypesPaginatedResponseDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.exception.ApiRequestException;
import site.deiv70.springboot.prototype.infrastructure.primary.mapper.PrototypeDtoMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class PrototypeApiRestController implements PrototypeApi {

	private final PrototypeDtoMapper prototypeDtoMapper;

	private final GetPrototypeByIdUseCase getPrototypeByIdUseCase;
	private final UpdatePrototypeByIdUseCase updatePrototypeByIdUseCase;
	private final DeletePrototypeByIdUseCase deletePrototypeByIdUseCase;

	private final GetPrototypesUseCase getPrototypesUseCase;
	private final CreatePrototypesUseCase createPrototypesUseCase;
	private final UpdatePrototypesUseCase updatePrototypesUseCase;
	private final DeletePrototypesUseCase deletePrototypesUseCase;

	@Override
	public ResponseEntity<PrototypeDtoModel> getPrototypeById(UUID prototypeId) {
		// 1. Always check parameters first
		if (prototypeId == null) {	// It can't be empty because it's a UUID type
			throw new ApiRequestException(ApiRequestException.Type.INVALID_PARAMETER_EXCEPTION,
				"Prototype id can't be null");
			// return ResponseEntity.badRequest().build();
		}
		// 2. Call the usecase
		Optional<PrototypeDtoModel> usecaseResponseOptional = getPrototypeByIdUseCase.execute(prototypeId)
																.map(prototypeDtoMapper::toPrototypeDtoModel);
		// 3. Check the usecaseResponse and return the corresponding
		return usecaseResponseOptional
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<PrototypeDtoModel> updatePrototypeById(
			UUID prototypeId, PrototypeUpdateRequestDtoModel updatedPrototypeDtoModel
	) {
		// 1. Always check parameters first
		if (prototypeId == null || updatedPrototypeDtoModel == null) {
			return ResponseEntity.badRequest().build();
		}

		// 2. Call the usecase
		Optional<PrototypeDtoModel> usecaseResponseOptional = updatePrototypeByIdUseCase.execute(
				prototypeId,
				prototypeDtoMapper.updatedToPrototypeModel(updatedPrototypeDtoModel)
			).map(prototypeDtoMapper::toPrototypeDtoModel);

		// 3. Check the usecaseResponse and return the corresponding
		return usecaseResponseOptional
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<Void> deletePrototypeById(UUID prototypeId) {
		if (prototypeId == null) {
			return ResponseEntity.badRequest().build();
		}

		deletePrototypeByIdUseCase.deletePrototypeById(prototypeId);

		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<PrototypesPaginatedResponseDtoModel> getPrototypes(
			String name,
			@PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
	) {
		PrototypesPaginatedResponseDtoModel prototypesPaginatedResponseDtoModel =
			new PrototypesPaginatedResponseDtoModel();
		List<PrototypeDtoModel> prototypeDtoModelList = null;

		if (name == null || name.isEmpty()) {
			prototypeDtoModelList = getPrototypesUseCase.getAllPrototypes(pageable)
				.map(prototypeDtoMapper::toPrototypeDtoModel)
				.getContent();
		} else {
			prototypeDtoModelList = getPrototypesUseCase.getPrototypesByName(name, pageable)
				.map(prototypeDtoMapper::toPrototypeDtoModel)
				.getContent();
		}

		prototypesPaginatedResponseDtoModel.setContent(prototypeDtoModelList);

		CriteriaDtoModel criteriaDtoModel = new CriteriaDtoModel();
		criteriaDtoModel.setPage(pageable.getPageNumber());
		criteriaDtoModel.setSize(pageable.getPageSize());
		criteriaDtoModel.setSort(List.of(String.valueOf(pageable.getSort())));

		prototypesPaginatedResponseDtoModel.setCriteria(
				criteriaDtoModel);

		return ResponseEntity.ok(prototypesPaginatedResponseDtoModel);
	}

	@Override
	public ResponseEntity<List<PrototypeDtoModel>> createPrototypes(List<PrototypeDtoModel> prototypeDtoModelList) {
		if (prototypeDtoModelList.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}

		List<PrototypeDtoModel> usecaseResponseList = prototypeDtoMapper.toPrototypeDtoModelList(
				createPrototypesUseCase.createPrototypes(
						prototypeDtoMapper.toPrototypeModelList(prototypeDtoModelList)));

		// Return a responseEntity 200 with responseIterable if it's not empty, or else return a 400
		return usecaseResponseList.iterator().hasNext()
				? ResponseEntity.ok(usecaseResponseList)
				: ResponseEntity.badRequest().build();
	}

	@Override
	public ResponseEntity<Void> deletePrototypes(List<IdDtoModel> idDtoModelList) {
		if (idDtoModelList.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}

		deletePrototypesUseCase.deletePrototypesById(idDtoModelList);

		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<PrototypeDtoModel>> updatePrototypes(List<PrototypeDtoModel> prototypeDtoModelList) {
		if (prototypeDtoModelList.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}

		List<PrototypeDtoModel> usecaseResponseList = prototypeDtoMapper.toPrototypeDtoModelList(
				updatePrototypesUseCase.updatePrototypes(
						prototypeDtoMapper.toPrototypeModelList(prototypeDtoModelList)));

		return ResponseEntity.ok(usecaseResponseList);
	}

}
