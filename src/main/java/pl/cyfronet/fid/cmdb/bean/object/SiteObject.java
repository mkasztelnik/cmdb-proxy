package pl.cyfronet.fid.cmdb.bean.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class SiteObject {
    @JsonProperty("_id")
    String id;
    Object info;
}