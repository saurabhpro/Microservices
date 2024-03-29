package com.department.api.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {

    /**
     * https://thorben-janssen.com/generate-uuids-primary-keys-hibernate/
     */
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @JsonProperty("departmentName")
    private String departmentName;

    @JsonProperty("capacity")
    private int capacity;

}
