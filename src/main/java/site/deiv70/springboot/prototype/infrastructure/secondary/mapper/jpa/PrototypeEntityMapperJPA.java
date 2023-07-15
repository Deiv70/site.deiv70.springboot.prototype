package site.deiv70.springboot.prototype.infrastructure.secondary.mapper.jpa;

import org.mapstruct.Mapper;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa.PrototypeEntityModelJPA;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface PrototypeEntityMapperJPA {

	PrototypeModel toPrototypeModel(PrototypeEntityModelJPA prototypeEntityModelJPA);
	PrototypeEntityModelJPA toPrototypeEntityModelJPA(PrototypeModel prototypeModel);

	List<PrototypeModel> toPrototypeModelList(List<PrototypeEntityModelJPA> prototypeEntityModelJPAList);
	List<PrototypeEntityModelJPA> toPrototypeEntityModelJPAList(List<PrototypeModel> prototypeModelList);

}
