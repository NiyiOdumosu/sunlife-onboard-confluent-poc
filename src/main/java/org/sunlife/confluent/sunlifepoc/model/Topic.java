package org.sunlife.confluent.sunlifepoc.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Topic {

    private @Id
    @GeneratedValue
    Long id;
    @NotNull
    private String name;

    @Min(value = 1, message = "Partitions can't be less than 1")
    private Long partitions;

    private String cleanupPolicy;
    @Min(value = 1)
    private Long minSyncReplicas;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Metadata metadata;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private ConsumerGroup consumerGroup;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Schema schema;
    @NotNull
    private String resourceGroup;

}
