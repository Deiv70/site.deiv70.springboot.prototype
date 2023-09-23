package site.deiv70.springboot.prototype.infrastructure.secondary.mapper.jpa;

import org.mapstruct.Mapper;
import site.deiv70.springboot.prototype.domain.model.entity.SubprototypeModel;
import site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa.SubprototypeEntityModelJPA;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SubprototypeEntityMapperJPA {

	SubprototypeModel toSubprototypeModel(SubprototypeEntityModelJPA subprototypeEntityModelJPA);
	SubprototypeEntityModelJPA toSubprototypeEntityModelJPA(SubprototypeModel subprototypeModel);

	List<SubprototypeModel> toSubprototypeModelList(List<SubprototypeEntityModelJPA> subprototypeEntityModelJPAList);
	List<SubprototypeEntityModelJPA> toSubprototypeEntityModelJPAList(List<SubprototypeModel> subprototypeModelList);

}
