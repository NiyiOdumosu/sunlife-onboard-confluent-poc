package org.sunlife.confluent.sunlifepoc.model;

import lombok.Data;

@Data
public class BitBucketClient {
    private String username;
    private String appToken;
    private String encodedCredentials;
    private String repositoryName;
    private String branchName;
    private String filename;
    private String sourceBranch;
    private String destinationBranch;
    private String pullRequestTitle;

}
