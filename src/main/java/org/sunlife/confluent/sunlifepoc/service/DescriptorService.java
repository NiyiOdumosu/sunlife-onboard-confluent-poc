package org.sunlife.confluent.sunlifepoc.service;



import org.sunlife.confluent.sunlifepoc.model.Topic;
import java.io.File;

public interface DescriptorService {
    public File buildDescriptorForTopic(Topic topic);

    }
