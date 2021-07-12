package com.department.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

//    @Value.Immutable
//    @Value.Style(
//            strictBuilder = true,
//            jdkOnly = true
//    //        builder = "new"
//    )
//    public static interface Department {
//        UUID getId();
//
//        String getDepartmentName();
//
//        int getCapacity();
//
//    }
}
