package org.sunlife.confluent.sunlifepoc.service;



import org.sunlife.confluent.sunlifepoc.model.ConsumerGroup;
import org.sunlife.confluent.sunlifepoc.model.Schema;
import org.sunlife.confluent.sunlifepoc.model.Topic;
import org.yaml.snakeyaml.Yaml;

import java.io.File;

public interface DescriptorService {
    public File buildDescriptorForTopic(Topic topic);

    public File buildDescriptorForSchema(Schema schema);

    public File buildDescriptorForConsumerGroup(ConsumerGroup topic);


//    public Yaml buildDescriptorForConnnector(ConsumerGroup topic);
}
