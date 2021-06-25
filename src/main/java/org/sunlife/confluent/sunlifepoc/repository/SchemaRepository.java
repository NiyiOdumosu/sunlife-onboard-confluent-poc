package org.sunlife.confluent.sunlifepoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sunlife.confluent.sunlifepoc.model.Schema;

@Repository
public interface SchemaRepository extends JpaRepository<Schema, Long> { }
