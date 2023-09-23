package site.deiv70.springboot.prototype.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubprototypeModel implements Serializable {

	private UUID id;
	private String name;
	private String description;
	private PrototypeModel prototypeModel;

}
