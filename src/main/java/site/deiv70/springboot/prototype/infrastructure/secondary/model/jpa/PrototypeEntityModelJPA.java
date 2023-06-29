package site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "prototype")
public class PrototypeEntityModelJPA {

    @Id
    private UUID id;
    private String name;
    private String description;

}
