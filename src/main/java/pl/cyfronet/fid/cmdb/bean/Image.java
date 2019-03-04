package pl.cyfronet.fid.cmdb.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class Image {
    @JsonProperty("_id")
    String id;
    @JsonProperty("_rev")
    String rev;
    Info info;

    @Data
    private class Info {
        @JsonProperty("SiteName")
        String siteName;
        @JsonProperty("SitePKey")
        String sitePKey;
        @JsonProperty("SiteEndpointPKey")
        String endpointPKey;
        @JsonProperty("GLUE2EndpointID")
        String endpointID;
        @JsonProperty("GLUE2EntityName")
        String enityName;
        @JsonProperty("GLUE2ApplicationEnvironmentRepository")
        String applicationEnvironmentRepository;
        @JsonProperty("ImageBaseMpUri")
        String imageBaseMpUri;
        @JsonProperty("ImageContentType")
        String imageContentType;
        @JsonProperty("ImageVoVmiInstanceVO")
        String imageVoVmiInstanceVO;
        @JsonProperty("ImageVoVmiInstanceId")
        int imageVoVmiInstanceId;
        @JsonProperty("ImageVmiInstanceId")
        int imageVmiInstanceId;
        @JsonProperty("ImageAppDBVAppID")
        int imageAppDBVAppID;
        @JsonProperty("GLUE2ApplicationEnvironmentAppVersion")
        String GLUE2ApplicationEnvironmentAppVersion;
        @JsonProperty("GLUE2ApplicationEnvironmentID")
        String GLUE2ApplicationEnvironmentID;
        @JsonProperty("GLUE2ApplicationEnvironmentAppName")
        String GLUE2ApplicationEnvironmentAppName;
        @JsonProperty("GLUE2ApplicationEnvironmentDescription")
        String GLUE2ApplicationEnvironmentDescription;
        @JsonProperty("GLUE2ApplicationEnvironmentComputingManagerForeignKey")
        String GLUE2ApplicationEnvironmentComputingManagerForeignKey;
        @JsonProperty("hash")
        String hash;
    }
}
