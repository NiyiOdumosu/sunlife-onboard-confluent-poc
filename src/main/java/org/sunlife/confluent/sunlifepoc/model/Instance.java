package org.sunlife.confluent.sunlifepoc.model;

import lombok.Data;

@Data
public class Instance {

    private String principal;

    private String topic;

    private String group;

    private String appId;
}
