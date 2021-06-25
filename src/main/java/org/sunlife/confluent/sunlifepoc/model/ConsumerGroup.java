package org.sunlife.confluent.sunlifepoc.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class ConsumerGroup {

    private @Id @GeneratedValue
    Long id;

    private String name;
}
