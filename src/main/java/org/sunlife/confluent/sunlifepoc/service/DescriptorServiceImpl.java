package org.sunlife.confluent.sunlifepoc.service;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.sunlife.confluent.sunlifepoc.bitbucket.BitBucket;
import org.sunlife.confluent.sunlifepoc.model.*;



import java.io.File;
import java.io.IOException;

@Service
public class DescriptorServiceImpl implements DescriptorService{

    private final static String descriptorConfig = "src/main/resources/descriptor.yaml";
    Logger logger = LoggerFactory.getLogger(DescriptorServiceImpl.class);

    @Override
    public File buildDescriptorForTopic(Topic topic) {

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        File descriptor = new File(descriptorConfig);
        BitBucket bitBucket = new BitBucket();

        try {
            objectMapper.writeValue(descriptor, setConfigValues(topic));
            bitBucket.createPR();
        } catch (IOException e) {
            logger.error("Failed due to the following error: %s", e.getMessage());
        }

        return descriptor;
    }


    @Override
    public File buildDescriptorForSchema(Schema schema) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        File descriptor = new File(descriptorConfig);

//        try {
//            objectMapper.writeValue(descriptor, setConfigValues(schema));
//        } catch (IOException e) {
//            logger.error("Failed to write the values to the yaml file due to: %s", e.getMessage());
//        }
        return descriptor;

    }

    @Override
    public File buildDescriptorForConsumerGroup(ConsumerGroup group) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        File descriptor = new File(descriptorConfig);

//        try {
//            objectMapper.writeValue(descriptor, setConfigValues(group));
//        } catch (IOException e) {
//            logger.error("Failed to write the values to the yaml file due to: %s", e.getMessage());
//        }

        return descriptor;
    }

    private DescriptorFile setConfigValues(Topic topic) {
        DescriptorFile file = new DescriptorFile();
        //build descriptor config file
        file.setContext("Test");
        file.setCompany("Sunlife");
        file.setEnv("stage");
        file.setSource("source");
        file.setProjects(addProject(topic));
        file.setPlatform(addPlatform(topic));

        return file;
    }

    private Project addProject(Topic topic) {
        //Build conusmer and producer
        Consumer consumer = new Consumer();
        consumer.setPrincipal("Group:"+ topic.getResourceGroup());
        Producer producer = new Producer();
        producer.setPrincipal("Group:"+ topic.getResourceGroup());

        //Build Topic Config
        Config config = new Config();
        config.setNumPartitions(topic.getPartitions());
        config.setReplicationFactor(topic.getMinSyncReplicas());

        TopicConfig topicConfig = new TopicConfig();
        topicConfig.setConfig(config);
        topicConfig.setName(topic.getName());
        topicConfig.setDataType(topic.getSchema().getDataType());

        //Build

        //Build project
        Project project = new Project();
        project.setConsumer(consumer);
        project.setProducer(producer);
        project.setTopics(topicConfig);

        return project;
    }

    private Platform addPlatform(Topic topic){
        Platform platform = new Platform();
        SchemaRegistry schemaRegistry = new SchemaRegistry();
        ControlCenter controlCenter = new ControlCenter();
        //Build Schema Registry Instance
        Instance instance1 = new Instance();
        instance1.setTopic(topic.getName());
        instance1.setPrincipal("Group:"+topic.getResourceGroup());
        instance1.setGroup(topic.getConsumerGroup().getName());
        schemaRegistry.setInstances(instance1);

        //Build Control Center Instance
        Instance instance2 = new Instance();
        instance2.setPrincipal("Group:"+topic.getResourceGroup());
        instance2.setAppId("controlcenter");
        controlCenter.setInstances(instance2);

        platform.setSchemaRegistry(schemaRegistry);
        platform.setControlCenter(controlCenter);

        return platform;
    }


}
