package pl.cyfronet.fid.cmdb.bean.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class ImageObject {
    @JsonProperty("_id")
    String id;
    Object info;
}
