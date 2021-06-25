package org.sunlife.confluent.sunlifepoc.service;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.sunlife.confluent.sunlifepoc.bitbucket.BitBucket;
import org.sunlife.confluent.sunlifepoc.model.*;
import org.sunlife.confluent.sunlifepoc.model.rbac.DeveloperRead;
import org.sunlife.confluent.sunlifepoc.model.rbac.DeveloperWrite;
import org.sunlife.confluent.sunlifepoc.model.rbac.RBAC;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


    private DescriptorFile setConfigValues(Topic topic) {
        DescriptorFile file = new DescriptorFile();
        //build descriptor config file
        file.setContext("Test");
        file.setCompany("Sunlife");
        file.setEnv("stage");
        file.setSource("source");
        file.setProjects(addProject(topic));


        return file;
    }

    private Project addProject(Topic topic) {
        //Build conusmer and producer
        Consumer consumer = new Consumer();
        consumer.setPrincipal("Group:"+ topic.getConsumerResourceGroup());
        Producer producer = new Producer();
        producer.setPrincipal("Group:"+ topic.getProducerResourceGroup());

        //Build Topic Config
        Config config = new Config();
        config.setNumPartitions(topic.getPartitions());
        config.setReplicationFactor(topic.getReplicationFactor());


        List<Principal> principals = new ArrayList<>();
        Principal p1 = new Principal();
        Principal p2 = new Principal();
        p1.setPrincipal(topic.getConsumerResourceGroup());
        p2.setPrincipal(topic.getProducerResourceGroup());
        principals.add(p1);
        principals.add(p2);

        TopicConfig topicConfig = new TopicConfig();
        topicConfig.setConfig(config);
        topicConfig.setName(topic.getName());
        topicConfig.setPrincipals(principals);
        topicConfig.setDataType(topic.getSchema().getDataType());

        //Build RBAC roles
        RBAC rbac = new RBAC();
        DeveloperRead developerRead = new DeveloperRead();
        developerRead.setPrincipal("Group:"+ topic.getConsumerResourceGroup());
        rbac.setDeveloperRead(developerRead);

        DeveloperWrite developerWrite = new DeveloperWrite();
        developerWrite.setPrincipal("Group:"+ topic.getProducerResourceGroup());
        rbac.setDeveloperWrite(developerWrite);


        //Build project
        Project project = new Project();
        project.setRbac(rbac);
        project.setSchema(topic.getSchema());
        project.setConsumer(consumer);
        project.setProducer(producer);
        project.setTopics(topicConfig);

        return project;
    }

    private Platform addPlatform(Topic topic){
        Platform platform = new Platform();
        SchemaRegistry schemaRegistry = new SchemaRegistry();
        //Build Schema Registry Instance
        Instance instance1 = new Instance();
        instance1.setTopic(topic.getName());
        instance1.setPrincipal("Group:"+topic.getConsumerResourceGroup());
        instance1.setGroup(topic.getConsumerGroup().getName());
        schemaRegistry.setInstances(instance1);


        platform.setSchemaRegistry(schemaRegistry);


        return platform;
    }


}
