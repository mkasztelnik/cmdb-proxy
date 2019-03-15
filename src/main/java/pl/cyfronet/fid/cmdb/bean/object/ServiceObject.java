package pl.cyfronet.fid.cmdb.bean.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ServiceObject {
    @JsonProperty("_id")
    String id;
    Object info;
}