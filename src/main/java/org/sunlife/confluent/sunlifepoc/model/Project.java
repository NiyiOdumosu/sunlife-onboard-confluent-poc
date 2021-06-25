package org.sunlife.confluent.sunlifepoc.model;

import lombok.Data;
import org.sunlife.confluent.sunlifepoc.model.rbac.RBAC;

@Data
public class Project {

    private String name;

    private RBAC rbac;

    private Consumer consumer;

    private Producer producer;

    private Streams streams;

    private Schema schema;

    private TopicConfig topics;

}
