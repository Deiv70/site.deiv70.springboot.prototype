package site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
// TODO: Make Table name configurable
@Table(name = "prototype")
public class PrototypeEntityModelJPA {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

	@Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String name;

	@Column(columnDefinition = "VARCHAR(100) NOT NULL")
    private String description;

	@OneToMany(mappedBy = "prototype")
	private List<SubPrototypeEntityModelJPA> subPrototypeEntityModelJPAList;

	@CreationTimestamp(source = SourceType.DB)
	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL")
	private Instant createdAt;

	@UpdateTimestamp(source = SourceType.DB)
	@Column(name = "last_modified_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP")
	private Instant lastModifiedAt;

}
