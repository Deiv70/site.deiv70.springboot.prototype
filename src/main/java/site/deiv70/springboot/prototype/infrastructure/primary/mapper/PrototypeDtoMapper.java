package site.deiv70.springboot.prototype.infrastructure.primary.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypeDtoModel;
import site.deiv70.springboot.prototype.infrastructure.primary.dto.PrototypeUpdateRequestDtoModel;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrototypeDtoMapper {

    PrototypeDtoModel toPrototypeDtoModel(PrototypeModel prototypeModel);
	PrototypeModel toPrototypeModel(PrototypeDtoModel prototypeDtoModel);

    List<PrototypeDtoModel> toPrototypeDtoModelList(List<PrototypeModel> prototypeModelList);
	List<PrototypeModel> toPrototypeModelList(List<PrototypeDtoModel> prototypeDtoModelList);

	PrototypeModel updatedToPrototypeModel(PrototypeUpdateRequestDtoModel updatedPrototypeDtoModel);
	List<PrototypeModel> updatedListToPrototypeModelList(List<PrototypeUpdateRequestDtoModel> updatedPrototypeDtoModelList);

}
