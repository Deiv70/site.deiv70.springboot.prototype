package site.deiv70.springboot.prototype.infrastructure.primary.controller.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.infrastructure.primary.api.PrototypeApi;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.CriteriaDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.IdDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypeDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypeUpdateRequestDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypesPaginatedResponseDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.mapper.PrototypeDtoMapper;

import java.util.List;
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
	public ResponseEntity<PrototypeDtoModel> getPrototypeById(
		@NotBlank final UUID prototypeId
	) {
		PrototypeDtoModel usecaseResponse = prototypeDtoMapper.toPrototypeDtoModel(
			getPrototypeByIdUseCase.execute(prototypeId)
		);

		return ResponseEntity.ok(usecaseResponse);
	}

	@Override
	public ResponseEntity<PrototypeDtoModel> updatePrototypeById(
			@NotBlank final UUID prototypeId,
			@Valid final PrototypeUpdateRequestDtoModel updatedPrototypeDtoModel
	) {
		PrototypeModel prototypeModel = prototypeDtoMapper.updatedToPrototypeModel(updatedPrototypeDtoModel);
		prototypeModel.setId(prototypeId);

		PrototypeDtoModel usecaseResponse = prototypeDtoMapper.toPrototypeDtoModel(
			updatePrototypeByIdUseCase.execute(prototypeModel)
		);

		return ResponseEntity.ok(usecaseResponse);
	}

	@Override
	public ResponseEntity<Void> deletePrototypeById(
		@NotBlank final UUID prototypeId
	) {
		deletePrototypeByIdUseCase.execute(prototypeId);

		return ResponseEntity.ok().build();
	}


	@Override
	public ResponseEntity<PrototypesPaginatedResponseDtoModel> getPrototypes(
			@NotBlank final String name,
			@PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) final Pageable pageable
	) {
		PrototypesPaginatedResponseDtoModel prototypesPaginatedResponseDtoModel =
			new PrototypesPaginatedResponseDtoModel();

		List<PrototypeDtoModel> prototypeDtoModelList = null;
		CriteriaDtoModel criteriaDtoModel = new CriteriaDtoModel();

		if (name == null || name.isEmpty()) {
			Page<PrototypeDtoModel> prototypeDtoModelPage = getPrototypesUseCase.getAllPrototypes(pageable)
				.map(prototypeDtoMapper::toPrototypeDtoModel);
			prototypeDtoModelList = prototypeDtoModelPage.getContent();

			criteriaDtoModel.setPage(prototypeDtoModelPage.getNumber());
			criteriaDtoModel.setSize(prototypeDtoModelPage.getSize());
			criteriaDtoModel.setSort(List.of(String.valueOf(prototypeDtoModelPage.getSort())));
		} else {
			Page<PrototypeDtoModel> prototypeDtoModelPage = getPrototypesUseCase.getPrototypesByName(name, pageable)
				.map(prototypeDtoMapper::toPrototypeDtoModel);
			prototypeDtoModelList = prototypeDtoModelPage.getContent();

			criteriaDtoModel.setPage(prototypeDtoModelPage.getNumber());
			criteriaDtoModel.setSize(prototypeDtoModelPage.getSize());
			criteriaDtoModel.setSort(List.of(String.valueOf(prototypeDtoModelPage.getSort())));
		}

		prototypesPaginatedResponseDtoModel.setContent(prototypeDtoModelList);
		prototypesPaginatedResponseDtoModel.setCriteria(criteriaDtoModel);

		return ResponseEntity.ok(prototypesPaginatedResponseDtoModel);
	}

	@Override
	public ResponseEntity<List<PrototypeDtoModel>> createPrototypes(
		@NotEmpty @Valid final List<PrototypeDtoModel> prototypeDtoModelList
	) {
		List<PrototypeDtoModel> usecaseResponseList = prototypeDtoMapper.toPrototypeDtoModelList(
				createPrototypesUseCase.execute(
						prototypeDtoMapper.toPrototypeModelList(prototypeDtoModelList)
				));

		return ResponseEntity.ok(usecaseResponseList);
	}

	@Override
	public ResponseEntity<List<PrototypeDtoModel>> updatePrototypes(
		@NotEmpty @Valid final List<PrototypeDtoModel> prototypeDtoModelList
	) {
		List<PrototypeDtoModel> usecaseResponseList = prototypeDtoMapper.toPrototypeDtoModelList(
				updatePrototypesUseCase.updatePrototypes(
						prototypeDtoMapper.toPrototypeModelList(prototypeDtoModelList)
				));

		return ResponseEntity.ok(usecaseResponseList);
	}

	@Override
	public ResponseEntity<Void> deletePrototypes(
		@NotEmpty @Valid final List<IdDtoModel> idDtoModelList
	) {
		deletePrototypesUseCase.execute(idDtoModelList);

		return ResponseEntity.ok().build();
	}

}
