package pl.cyfronet.fid.cmdb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.cyfronet.fid.cmdb.bean.object.*;

import java.io.IOException;

@Log4j
@RestController
public class RestPutController {

    @Value("${cmdb.url}")
    private String cmdbUrl;

    @Value("${couchdb.login}")
    private String login;

    @Value("${couchdb.password}")
    private String password;

    private ObjectMapper mapper = new ObjectMapper() {
        private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

        @Override
        public <T> T readValue(String s, Class<T> aClass) {
            try {
                return jacksonObjectMapper.readValue(s, aClass);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String writeValue(Object o) {
            try {
                return jacksonObjectMapper.writeValueAsString(o);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    };

    @RequestMapping(value = "/site", method = RequestMethod.PUT)
    public @ResponseBody
    String updateSite(@RequestBody SiteObject site) throws UnirestException {
        String siteId = site.getId();
        site.setRev(Unirest.get(cmdbUrl + "/" + siteId).asJson().getBody().getObject().getString("_rev"));

        return getHttpRequestWithBody(mapper.writeValue(site), siteId);
    }

    @RequestMapping(value = "/service", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String updateService(@RequestBody ServiceObject service) throws UnirestException {
        String serviceId = service.getId();
        service.setRev(Unirest.get(cmdbUrl + "/" + serviceId).asJson().getBody().getObject().getString("_rev"));

        return getHttpRequestWithBody(mapper.writeValue(service), serviceId);
    }

    @RequestMapping(value = "/image", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String updateImage(@RequestBody ImageObject image) throws UnirestException {
        String imageId = image.getId();
        image.setRev(Unirest.get(cmdbUrl + "/" + imageId).asJson().getBody().getObject().getString("_rev"));

        return getHttpRequestWithBody(mapper.writeValue(image), imageId);
    }

    private String getHttpRequestWithBody(String entity, String id) throws UnirestException {
        HttpRequestWithBody request = Unirest.put(cmdbUrl + "/" + id).basicAuth(login, password);
        request.body(entity);
        return request.asString().getBody();
    }

}
