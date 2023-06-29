package site.deiv70.springboot.prototype.infrastructure.primary.controller.rest;

import site.deiv70.springboot.prototype.infrastructure.primary.api.PrototypeApi;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypeDtoModel;
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

@RestController
@RequestMapping("api/")
public class PrototypeApiRestController implements PrototypeApi {

    private PrototypeDtoMapper prototypeDtoMapper;
    private GetPrototypeByIdUseCase getPrototypeByIdUseCase;
    private CreatePrototypesUseCase createPrototypesUseCase;
    private GetPrototypesUseCase getPrototypesUseCase;

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
    public ResponseEntity<List<PrototypeDtoModel>> getPrototypes(String name, Pageable pageable) {
        // If no name field is send: return a responseEntity 200 with responseIterable
        if (name.isEmpty()) {
            return ResponseEntity.ok(
                    prototypeDtoMapper.toPrototypeDtoModelList(
                            getPrototypesUseCase.getAllPrototypes()
                    )
            );
        }

        // Return a responseEntity 200 with responseIterable
        return ResponseEntity.ok(
                prototypeDtoMapper.toPrototypeDtoModelList(
                        getPrototypesUseCase.getPrototypesByName(name)
                )
        );
    }

    @Override
    public ResponseEntity<List<PrototypeDtoModel>> addPrototypes(List<PrototypeDtoModel> prototypeDtoModelList) {
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

    @Override
    public ResponseEntity<Void> deletePrototypeById(UUID prototypeId) {
        return ResponseEntity.ok().build();
    }

}
