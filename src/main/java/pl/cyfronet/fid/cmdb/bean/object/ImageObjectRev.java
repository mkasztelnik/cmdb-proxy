package pl.cyfronet.fid.cmdb.bean.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class ImageObjectRev {
    @JsonProperty("_id")
    String id;
    @JsonProperty("_rev")
    String rev;
    Object info;
}
