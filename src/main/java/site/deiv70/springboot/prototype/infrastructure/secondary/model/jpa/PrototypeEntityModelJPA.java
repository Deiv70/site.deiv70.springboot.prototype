package site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "prototype")
public class PrototypeEntityModelJPA {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(columnDefinition = "VARCHAR(30) NOT NULL")
	private String name;

	@Column(columnDefinition = "VARCHAR(100) NOT NULL")
	private String description;

	@OneToMany(mappedBy = "prototype")
	private List<SubPrototypeEntityModelJPA> subPrototypeEntityModelJPAList;

	// @CreationTimestamp(source = SourceType.DB)
	@CreatedDate
	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL")
	private Instant createdAt;

	// @UpdateTimestamp(source = SourceType.DB)
	@LastModifiedDate
	@Column(name = "last_modified_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP")
	private Instant lastModifiedAt;

}
