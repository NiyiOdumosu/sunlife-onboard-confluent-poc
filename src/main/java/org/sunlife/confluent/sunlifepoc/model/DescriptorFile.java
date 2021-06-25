package org.sunlife.confluent.sunlifepoc.model;

import lombok.Data;
import org.sunlife.confluent.sunlifepoc.model.rbac.RBAC;

@Data
public class DescriptorFile {
   private String context;

   private String company;

   private String env;

   private String source;

   private Project projects;

   private Platform platform;

}
