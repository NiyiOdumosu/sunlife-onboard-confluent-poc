package org.sunlife.confluent.sunlifepoc.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Schema {

    private  @Id
    @GeneratedValue
    Long id;

    private String principal;

    private String subject;

    private String dataType;

    private String valueSchemaFile;

    private String keySchemaFile;
}
