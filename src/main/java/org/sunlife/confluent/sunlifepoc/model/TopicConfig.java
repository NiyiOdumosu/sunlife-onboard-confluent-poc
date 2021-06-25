package org.sunlife.confluent.sunlifepoc.model;

import lombok.Data;

@Data
public class TopicConfig {

    private String name;
    private Config config;
    private String read;
    private String write;
    private String dataType;

}
