package site.deiv70.springboot.prototype.domain.model.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotNull
	@NotBlank
    private String name;
	@NotNull
	@NotBlank
    private String description;
	private List<SubprototypeModel> subprototypeModelList;
	private Instant createdAt;
	private Instant lastModifiedAt;

}
