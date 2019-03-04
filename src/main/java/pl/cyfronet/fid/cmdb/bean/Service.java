package pl.cyfronet.fid.cmdb.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Service {
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
        @JsonProperty("SiteEndpointInProduction")
        boolean isInProduction;
        @JsonProperty("SiteEndpointBeta")
        boolean beta;
        @JsonProperty("SiteEndpointGocPortalUrl")
        String gocPortalUrl;
        @JsonProperty("SiteEndpointUrl")
        String gocEndpointUrl;
        @JsonProperty("GLUE2EndpointURL")
        String endpointURL;
        @JsonProperty("SiteEndpointServiceType")
        String endpointServiceType;
        @JsonProperty("GLUE2EndpointID")
        String endpointID;
        @JsonProperty("GLUE2EndpointInterfaceName")
        String endpointInterfaceName;
        @JsonProperty("GLUE2EndpointInterfaceVersion")
        String endpointInterfaceVersion;
        @JsonProperty("GLUE2EndpointTechnology")
        String endpointTechnology;
        @JsonProperty("GLUE2EndpointQualityLevel")
        String endpointQualityLevel;
        @JsonProperty("GLUE2EndpointCapability")
        List<String> endpointCapabilities;
        @JsonProperty("GLUE2EndpointServingState")
        String endpointServingState;
        @JsonProperty("GLUE2EndpointHealthState")
        String endpointHealthState;
        @JsonProperty("GLUE2EndpointImplementor")
        String endpointImplementor;
        @JsonProperty("GLUE2EndpointImplementationVersion")
        String endpointImplementationVersion;
        @JsonProperty("GLUE2LocationID.GLUE2LocationID")
        String locationId;
        @JsonProperty("GLUE2LocationID.GLUE2LocationLongitude")
        String locationLongitude;
        @JsonProperty("GLUE2LocationID.GLUE2LocationLatitude")
        String locationLatitude;
        @JsonProperty("GLUE2LocationID.GLUE2LocationCountry")
        String locationCountry;
        @JsonProperty("GLUE2LocationID.GLUE2LocationDomainForeignKey")
        String locationDomainForeignKey;
        @JsonProperty("GLUE2ComputingEndpointComputingServiceForeignKey")
        String computingEndpointComputingServiceForeignKey;
        @JsonProperty("GLUE2EndpointServiceForeignKey")
        String endpointServiceForeignKey;
        @JsonProperty("GLUE2ManagerID")
        String managerID;
        @JsonProperty("GLUE2ManagerProductName")
        String managerProductName;
        @JsonProperty("GLUE2ManagerProductVersion")
        String managerProductVersion;
        @JsonProperty("GLUE2ComputingManagerTotalLogicalCPUs")
        String computingManagerTotalLogicalCPUs;
        @JsonProperty("GLUE2ComputingManagerWorkingAreaTotal")
        String computingManagerWorkingAreaTotal;
        @JsonProperty("GLUE2EntityOtherInfo")
        String entityOtherInfo;
        @JsonProperty("images")
        //TODO: List type!
        List<String> imageList;
//        @JsonProperty("num_images")
//        String numberOfImages;
        @JsonProperty("templates")
        //TODO: List type!
        List<String> templateList;
//        @JsonProperty("GLUE2EndpointHealthState")
//        String numberOfTemplates;
        @JsonProperty("hash")
        String hash;
    }
}