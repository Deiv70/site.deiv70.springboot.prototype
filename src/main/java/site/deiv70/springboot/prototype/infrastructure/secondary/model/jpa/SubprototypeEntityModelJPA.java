package site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
// TODO: Make Table name configurable
@Table(name = "subprototype")
public class SubprototypeEntityModelJPA {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false, columnDefinition = "VARCHAR(30)")
	private String name;

	@Column(nullable = false, columnDefinition = "VARCHAR(100)")
	private String description;

	@ManyToOne
	@JoinColumn(name = "prototype_id")
	private PrototypeEntityModelJPA prototype;

}
