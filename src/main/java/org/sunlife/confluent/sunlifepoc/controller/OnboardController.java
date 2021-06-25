package org.sunlife.confluent.sunlifepoc.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sunlife.confluent.sunlifepoc.exceptions.ResourceNotFoundException;
import org.sunlife.confluent.sunlifepoc.model.ConsumerGroup;
import org.sunlife.confluent.sunlifepoc.model.Metadata;
import org.sunlife.confluent.sunlifepoc.model.Schema;
import org.sunlife.confluent.sunlifepoc.model.Topic;
import org.sunlife.confluent.sunlifepoc.model.rbac.RBAC;
import org.sunlife.confluent.sunlifepoc.repository.ConsumerGroupRepository;
import org.sunlife.confluent.sunlifepoc.repository.MetadataRepository;
import org.sunlife.confluent.sunlifepoc.repository.SchemaRepository;
import org.sunlife.confluent.sunlifepoc.repository.TopicRepository;
import org.sunlife.confluent.sunlifepoc.service.DescriptorService;
import org.sunlife.confluent.sunlifepoc.service.DescriptorServiceImpl;


import java.util.List;


@RestController
@RequestMapping("/onboard")
public class OnboardController {

    @Autowired
    private MetadataRepository metadataRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private SchemaRepository schemaRepository;
    @Autowired
    private ConsumerGroupRepository consumerGroupRepository;
    @Autowired
    private DescriptorServiceImpl descriptorService;

    Logger logger = LoggerFactory.getLogger(OnboardController.class);

    @GetMapping("/topics")
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @PostMapping("/topics")
    public Topic newTopic(@RequestBody Topic newTopic) {
        descriptorService.buildDescriptorForTopic(newTopic);
        logger.info("Created new topic for %s", newTopic.getName());
        return topicRepository.save(newTopic);
    }

    // Single item
    @GetMapping("/topics/{id}")
    public Topic findTopic(@PathVariable Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @DeleteMapping("/topics/{id}")
    public void removeTopic(@PathVariable Long id) {
        topicRepository.deleteById(id);
    }

    @GetMapping("/schemas")
    public List<Schema> getAllSchemas() {
        return schemaRepository.findAll();
    }

    @PostMapping("/schemas")
    public Schema newSchema(@RequestBody Schema schema) {
        return schemaRepository.save(schema);
    }

    // Single item
    @GetMapping("/schemas/{id}")
    public Schema findSchema(@PathVariable Long id) {
        return schemaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @DeleteMapping("/schemas/{id}")
    public void removeSchema(@PathVariable Long id) {
         schemaRepository.deleteById(id);
    }

    @GetMapping("/consumer-groups")
    public List<ConsumerGroup> getAllGroups() {
        return consumerGroupRepository.findAll();
    }

    @PostMapping("/consumer-groups")
    public ConsumerGroup newConsumerGroup(@RequestBody ConsumerGroup consumerGroup) {
        return consumerGroupRepository.save(consumerGroup);
    }

    // Single Consumer Group
    @GetMapping("/consumer-groups/{id}")
    public ConsumerGroup findConsumerGroup(@PathVariable Long id) {
        return consumerGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @DeleteMapping("/consumer-groups/{id}")
    public void removeConsumerGroup(@PathVariable Long id) {
         consumerGroupRepository.deleteById(id);
    }

    @GetMapping("/metadata")
    public List<Metadata> getAllMetadata() {
        return metadataRepository.findAll();
    }

    @PostMapping("/metadata")
    public Metadata newMetadata(@RequestBody Metadata consumerGroup) {
        return metadataRepository.save(consumerGroup);
    }

    // Single Metadata
    @GetMapping("/metadata/{id}")
    public Metadata findMetadata(@PathVariable Long id) {
        return metadataRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @DeleteMapping("/metadata/{id}")
    public void removeMetadata(@PathVariable Long id) {
        metadataRepository.deleteById(id);
    }


    @PostMapping("/rbac")
    public RBAC newRBAC(@RequestBody RBAC newRBAC) {
        logger.info("Created a new role for %s", newRBAC);
        return newRBAC;
    }
}
