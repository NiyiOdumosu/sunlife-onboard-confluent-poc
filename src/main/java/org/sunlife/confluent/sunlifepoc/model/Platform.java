package org.sunlife.confluent.sunlifepoc.model;

import lombok.Data;

@Data
public class Platform {
    private SchemaRegistry schemaRegistry;

    private ControlCenter controlCenter;

}
