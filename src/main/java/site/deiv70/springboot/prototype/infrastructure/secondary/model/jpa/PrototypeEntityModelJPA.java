package site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

}
