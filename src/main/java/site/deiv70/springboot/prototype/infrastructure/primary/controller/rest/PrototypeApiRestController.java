package site.deiv70.springboot.prototype.infrastructure.primary.controller.rest;

import lombok.AllArgsConstructor;
import site.deiv70.springboot.prototype.application.usecase.DeletePrototypeByIdUseCase;
import site.deiv70.springboot.prototype.application.usecase.UpdatePrototypeByIdUseCase;
import site.deiv70.springboot.prototype.infrastructure.primary.api.PrototypeApi;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.ApiErrorResponseDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.CriteriaDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypeDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypeUpdateRequestDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypesCreationResponseDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.mapper.PrototypeDtoMapper;

import site.deiv70.springboot.prototype.application.usecase.CreatePrototypesUseCase;
import site.deiv70.springboot.prototype.application.usecase.GetPrototypeByIdUseCase;
import site.deiv70.springboot.prototype.application.usecase.GetPrototypesUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Override
    public ResponseEntity<PrototypeDtoModel> getPrototypeById(UUID prototypeId) {
        Optional<PrototypeDtoModel> usecaseResponseOptional =
                getPrototypeByIdUseCase.getPrototypeById(prototypeId)
                        .map(prototypeDtoMapper::toPrototypeDtoModel);

        // Return a responseEntity 200 with usecaseResponseOptional if it's present, or else return a 404
        return usecaseResponseOptional
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

	@Override
	public ResponseEntity<PrototypeDtoModel> updatePrototypeById(UUID prototypeId, PrototypeUpdateRequestDtoModel updatedPrototype) {
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<Void> deletePrototypeById(UUID prototypeId) {
		return ResponseEntity.ok().build();
	}

    @Override
    public ResponseEntity<PrototypesCreationResponseDtoModel> getPrototypes(String name, Pageable pageable) {
		PrototypesCreationResponseDtoModel prototypesCreationResponseDtoModel = new PrototypesCreationResponseDtoModel();

		// If no name field is send: return a responseEntity 200 with responseIterable
        if (name == null || name.isEmpty()) {
			prototypesCreationResponseDtoModel.setPrototypes(
				prototypeDtoMapper.toPrototypeDtoModelList(
					getPrototypesUseCase.getAllPrototypes()
				)
			);
            return ResponseEntity.ok(prototypesCreationResponseDtoModel);
        } else {
			// Return a responseEntity 200 with responseIterable
			prototypesCreationResponseDtoModel.setPrototypes(
				prototypeDtoMapper.toPrototypeDtoModelList(
					getPrototypesUseCase.getPrototypesByName(name)
				)
			);
		}

		CriteriaDtoModel criteriaDtoModel = new CriteriaDtoModel();
		criteriaDtoModel.setPage(pageable.getPageNumber());
		criteriaDtoModel.setSize(pageable.getPageSize());
		criteriaDtoModel.setSort(List.of(String.valueOf(pageable.getSort())));
		prototypesCreationResponseDtoModel.setCriteria(
			criteriaDtoModel
		);
        return ResponseEntity.ok(prototypesCreationResponseDtoModel);
    }

    @Override
    public ResponseEntity<List<PrototypeDtoModel>> createPrototypes(List<PrototypeDtoModel> prototypeDtoModelList) {
        if (prototypeDtoModelList.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Iterable<PrototypeDtoModel> usecaseResponseIterable = prototypeDtoMapper.toPrototypeDtoModelIterable(
                createPrototypesUseCase.createPrototypes(
                        prototypeDtoMapper.toPrototypeModelIterable(prototypeDtoModelList)
                )
        );

        // Return a responseEntity 200 with responseIterable if it's not empty, or else return a 400
        return usecaseResponseIterable.iterator().hasNext()
                ? ResponseEntity.ok(prototypeDtoMapper.dtoIterableToDtoList(usecaseResponseIterable))
                : ResponseEntity.badRequest().build();
    }

}
