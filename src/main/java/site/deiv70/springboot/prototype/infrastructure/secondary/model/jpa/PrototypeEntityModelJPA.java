package site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
// TODO: Make Table name configurable
@Table(name = "prototype", indexes = {})
public class PrototypeEntityModelJPA {

	@Id
	//@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false, columnDefinition = "VARCHAR(30)")
	private String name;

	@Column(nullable = false, columnDefinition = "VARCHAR(100)")
	private String description;

	@OneToMany(mappedBy = "prototype")
	private List<SubPrototypeEntityModelJPA> subPrototypeEntityModelJPAList;

	// @CreationTimestamp(source = SourceType.DB) // Hibernate
	@CreatedDate
	@Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMPTZ")
	private Instant createdAt;

	// @UpdateTimestamp(source = SourceType.DB) // Hibernate
	@LastModifiedDate
	@Column(name = "last_modified_at", nullable = false, columnDefinition = "TIMESTAMPTZ")
	private Instant lastModifiedAt;

}
