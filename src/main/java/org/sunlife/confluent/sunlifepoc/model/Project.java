package org.sunlife.confluent.sunlifepoc.model;

import lombok.Data;

@Data
public class Project {

    private String name;

    private Consumer consumer;

    private Producer producer;

    private Streams streams;

    private TopicConfig topics;


}
