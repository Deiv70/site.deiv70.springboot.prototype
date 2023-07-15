package site.deiv70.springboot.prototype.infrastructure.primary.controller.rest;

import lombok.AllArgsConstructor;
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
import site.deiv70.springboot.prototype.infrastructure.primary.dto.IdRequestDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypeDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypeUpdateRequestDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypesPaginatedResponseDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.mapper.PrototypeDtoMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("api/")
public class PrototypeApiRestController implements PrototypeApi {

    private PrototypeDtoMapper prototypeDtoMapper;

    private GetPrototypeByIdUseCase getPrototypeByIdUseCase;
	private UpdatePrototypeByIdUseCase updatePrototypeByIdUseCase;
	private DeletePrototypeByIdUseCase deletePrototypeByIdUseCase;

    private GetPrototypesUseCase getPrototypesUseCase;
	private CreatePrototypesUseCase createPrototypesUseCase;
	private UpdatePrototypesUseCase updatePrototypesUseCase;
	private DeletePrototypesUseCase deletePrototypesUseCase;

	@Override
    public ResponseEntity<PrototypeDtoModel> getPrototypeById(UUID prototypeId) {
		if (prototypeId == null) {
			return ResponseEntity.badRequest().build();
		}

        Optional<PrototypeDtoModel> usecaseResponseOptional =
                getPrototypeByIdUseCase.getPrototypeById(prototypeId)
                        .map(prototypeDtoMapper::toPrototypeDtoModel);

        // Return a responseEntity 200 with usecaseResponseOptional if it's present, or else return a 404
        return usecaseResponseOptional
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

	@Override
	public ResponseEntity<PrototypeDtoModel> updatePrototypeById(
		UUID prototypeId, PrototypeUpdateRequestDtoModel updatedPrototypeDtoModel
	) {
		if (prototypeId == null || updatedPrototypeDtoModel == null) {
			return ResponseEntity.badRequest().build();
		}

		Optional<PrototypeDtoModel> usecaseResponseOptional =
			updatePrototypeByIdUseCase.updatePrototypeById(
				prototypeId,
				prototypeDtoMapper.updatedToPrototypeModel(updatedPrototypeDtoModel)
			)
				.map(prototypeDtoMapper::toPrototypeDtoModel);

		return usecaseResponseOptional
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
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
		PrototypesPaginatedResponseDtoModel prototypesPaginatedResponseDtoModel = new PrototypesPaginatedResponseDtoModel();
		List<PrototypeDtoModel> prototypeDtoModelList = null;

		if (name == null || name.isEmpty()) {
			prototypeDtoModelList =
				getPrototypesUseCase.getAllPrototypes(pageable).map(
					prototypeDtoMapper::toPrototypeDtoModel
				).getContent();
        } else {
			prototypeDtoModelList =
				getPrototypesUseCase.getPrototypesByName(name, pageable).map(
					prototypeDtoMapper::toPrototypeDtoModel
				).getContent();
		}

		prototypesPaginatedResponseDtoModel.setContent(prototypeDtoModelList);

		CriteriaDtoModel criteriaDtoModel = new CriteriaDtoModel();
		criteriaDtoModel.setPage(pageable.getPageNumber());
		criteriaDtoModel.setSize(pageable.getPageSize());
		criteriaDtoModel.setSort(List.of(String.valueOf(pageable.getSort())));

		prototypesPaginatedResponseDtoModel.setCriteria(
			criteriaDtoModel
		);

		return ResponseEntity.ok(prototypesPaginatedResponseDtoModel);
    }

    @Override
    public ResponseEntity<List<PrototypeDtoModel>> createPrototypes(List<PrototypeDtoModel> prototypeDtoModelList) {
        if (prototypeDtoModelList.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<PrototypeDtoModel> usecaseResponseList = prototypeDtoMapper.toPrototypeDtoModelList(
                createPrototypesUseCase.createPrototypes(
                        prototypeDtoMapper.toPrototypeModelList(prototypeDtoModelList)
                )
        );

        // Return a responseEntity 200 with responseIterable if it's not empty, or else return a 400
        return usecaseResponseList.iterator().hasNext()
                ? ResponseEntity.ok(usecaseResponseList)
                : ResponseEntity.badRequest().build();
    }

	@Override
	public ResponseEntity<Void> deletePrototypes(List<IdRequestDtoModel> idRequestDtoModelList) {
		if (idRequestDtoModelList.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}

		deletePrototypesUseCase.deletePrototypesById(idRequestDtoModelList);

		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<PrototypeDtoModel>> updatePrototypes(List<PrototypeDtoModel> prototypeDtoModelList) {
		if (prototypeDtoModelList.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}

		List<PrototypeDtoModel> usecaseResponseList = prototypeDtoMapper.toPrototypeDtoModelList(
			updatePrototypesUseCase.updatePrototypes(
				prototypeDtoMapper.toPrototypeModelList(prototypeDtoModelList)
			)
		);

		return ResponseEntity.ok(usecaseResponseList);
	}

}
