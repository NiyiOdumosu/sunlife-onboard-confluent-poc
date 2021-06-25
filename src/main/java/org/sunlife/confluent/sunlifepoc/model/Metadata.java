package org.sunlife.confluent.sunlifepoc.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Metadata {

   private @Id @GeneratedValue
   Long id;

   private String owner;

   private String resourceType;

   private String resourceName;

   private String costCenter;

   private String emergencyContact;

   private Boolean isPII;

   private Boolean isGovtRestricted;
}
