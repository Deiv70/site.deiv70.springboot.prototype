package site.deiv70.springboot.prototype.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrototypeModel implements Serializable {

    private UUID id;
    private String name;
    private String description;
	private List<SubPrototypeModel> subPrototypeModelList;
	private Instant createdAt;
	private Instant lastModifiedAt;

}
