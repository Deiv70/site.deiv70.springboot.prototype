package site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
