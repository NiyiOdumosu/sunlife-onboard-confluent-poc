package org.sunlife.confluent.sunlifepoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sunlife.confluent.sunlifepoc.model.ConsumerGroup;

@Repository
public interface ConsumerGroupRepository extends JpaRepository<ConsumerGroup, Long> { }
