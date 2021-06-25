package org.sunlife.confluent.sunlifepoc.bitbucket;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sunlife.confluent.sunlifepoc.model.BitBucketClient;
import 	com.atlassian.bitbucket.pull.PullRequestCreateRequest;
import 	com.atlassian.bitbucket.pull.PullRequest;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BitBucket {


    Logger logger = LoggerFactory.getLogger(BitBucket.class);

    private static final String bitbucketConfig = "src/main/resources/bitbucket.properties";


    public  void createPR() throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        BitBucketClient client = mapper.readValue(new File(bitbucketConfig), BitBucketClient.class);

        String pullRequestData = String.format("{\"title\":\"%s\",\"source\":{\"branch\":{\"name\":\"%s\"}},\"destination\":{\"branch\":{\"name\":\"%s\"}}}",client.getPullRequestTitle(),client.getSourceBranch(), client.getDestinationBranch()); //format of data required for rest API call.


        byte[] prRequestData = pullRequestData.getBytes(StandardCharsets.UTF_8);
        int prRequestDataLen = prRequestData.length;
        URL prURL = new URL ("https://api.bitbucket.org/2.0/repositories/"+client.getUsername()+"/"+client.getBranchName()+"/pullrequests");
        HttpsURLConnection prConnection = (HttpsURLConnection) prURL.openConnection();
        //For authentication
        prConnection.addRequestProperty("Authorization", "Basic "+client.getEncodedCredentials());
        prConnection.setRequestMethod("POST");
        //For setting the metadata in the POST request
        prConnection.setRequestProperty("charset", "utf-8");
        prConnection.setRequestProperty("Content-Type", "application/json");
        prConnection.setRequestProperty("Content-Length",Integer.toString(prRequestDataLen));
        prConnection.setDoOutput(true);
        try(DataOutputStream dataOutputStream = new DataOutputStream(prConnection.getOutputStream())) {
            dataOutputStream.write(prRequestData);
        }
        try{
            prConnection.connect();
            logger.info(prConnection.getResponseCode()+" "+prConnection.getResponseMessage());

        }catch (Exception e){
            logger.error("Pull request failed due to %s", e.getMessage());
        }
    }


}
