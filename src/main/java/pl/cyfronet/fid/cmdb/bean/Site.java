package pl.cyfronet.fid.cmdb.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class Site {
    @JsonProperty("_id")
    String id;
    @JsonProperty("_rev")
    String rev;
    Info info;

    @Data
    private class Info {
        @JsonProperty("SitePKey")
        String pkey;
        @JsonProperty("SiteName")
        String name;
        @JsonProperty("SiteShortName")
        String shortName;
        @JsonProperty("SiteOfficialName")
        String officialName;
        @JsonProperty("SiteDescription")
        String description;
        @JsonProperty("SiteGocdbPortalUrl")
        String gocdbPortalUrl;
        @JsonProperty("SiteHomeUrl")
        String homeUrl;
        @JsonProperty("SiteGiisUrl")
        String giisUrl;
        @JsonProperty("SiteCountryCode")
        String countryCode;
        @JsonProperty("SiteCountry")
        String country;
        @JsonProperty("SiteTier")
        String tier;
        @JsonProperty("SiteSubgrid")
        String subgrid;
        @JsonProperty("SiteRoc")
        String roc;
        @JsonProperty("SiteProdInfrastructure")
        String prodInfrastructure;
        @JsonProperty("SiteCertStatus")
        String certStatus;
        @JsonProperty("SiteTimezone")
        String timezone;
        @JsonProperty("SiteLatitude")
        Float latitude;
        @JsonProperty("SiteLongitude")
        Float longitude;
        @JsonProperty("SiteDomainname")
        String domainName;
        @JsonProperty("hash")
        String hash;
        @JsonProperty("indigoSpecific")
        Map<String,Object> indigoSpecific;
    }
}