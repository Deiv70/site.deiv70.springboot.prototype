package site.deiv70.springboot.prototype.infrastructure.primary.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypeDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypesCreationResponseDtoModel;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrototypeDtoMapper {

    List<PrototypeDtoModel> dtoIterableToDtoList(Iterable<PrototypeDtoModel> prototypeDtoModelIterable);

    PrototypeDtoModel toPrototypeDtoModel(PrototypeModel prototypeModel);
    List<PrototypeDtoModel> toPrototypeDtoModelList(Iterable<PrototypeModel> prototypeModelIterable);
    Iterable<PrototypeDtoModel> toPrototypeDtoModelIterable(Iterable<PrototypeModel> prototypeModelIterable);

    PrototypeModel toPrototypeModel(PrototypeDtoModel prototypeDtoModel);
    Iterable<PrototypeModel> toPrototypeModelIterable(List<PrototypeDtoModel> prototypeDtoModelList);

}
