package site.deiv70.springboot.prototype.infrastructure.secondary.model.jpa;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "${vars.db.table-name}")
public class PrototypeEntityModelJPA {

    @Id
    private UUID id;
    private String name;
    private String description;

}
