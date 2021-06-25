package org.sunlife.confluent.sunlifepoc.model;

import lombok.Data;

@Data
public class Config {

    private Long replicationFactor;
    private Long numPartitions;
}
