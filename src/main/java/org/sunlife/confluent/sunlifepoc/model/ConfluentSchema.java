package org.sunlife.confluent.sunlifepoc.model;

import lombok.Data;

@Data
public class ConfluentSchema {

    private String principal;

    private String subject;

    private String dataType;

    private String valueSchemaFile;

    private String keySchemaFile;
}
