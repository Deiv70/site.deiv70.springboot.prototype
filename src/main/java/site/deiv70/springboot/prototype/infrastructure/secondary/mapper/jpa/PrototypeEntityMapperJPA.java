package site.deiv70.springboot.prototype.infrastructure.secondary.mapper.jpa;

import org.mapstruct.Mapper;
import site.deiv70.springboot.prototype.domain.model.entity.PrototypeModel;
import site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa.PrototypeEntityModelJPA;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface PrototypeEntityMapperJPA {

    PrototypeModel toPrototypeModel(PrototypeEntityModelJPA prototypeEntityModelJPA);
    Iterable<PrototypeModel> toPrototypeModelIterable(Iterable<PrototypeEntityModelJPA> prototypeEntityModelJPAIterable);

    PrototypeEntityModelJPA toPrototypeEntityModelJPA(PrototypeModel prototypeModel);
    Iterable<PrototypeEntityModelJPA> toPrototypeEntityModelJPAIterable(Iterable<PrototypeModel> prototypeModelIterable);

}
