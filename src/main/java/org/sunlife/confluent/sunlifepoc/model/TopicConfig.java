package org.sunlife.confluent.sunlifepoc.model;

import lombok.Data;

import java.util.List;

@Data
public class TopicConfig {

    private String name;
    private List<Principal> principals;
    private Config config;
    private String read;
    private String write;
    private String dataType;


}
