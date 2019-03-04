package pl.cyfronet.fid.cmdb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.cyfronet.fid.cmdb.bean.Image;
import pl.cyfronet.fid.cmdb.bean.Service;
import pl.cyfronet.fid.cmdb.bean.Site;

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
    String updateSite(@RequestBody Site site) throws UnirestException {
        String jsonInString = mapper.writeValue(site);
        HttpRequestWithBody request = Unirest.put(cmdbUrl+site.getId()).basicAuth(login, password);
        request.body(jsonInString);
        return request.asString().getBody();
    }

    @RequestMapping(value = "/service", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String updateService(@RequestBody Service service) throws UnirestException {
        String jsonInString = mapper.writeValue(service);
        HttpRequestWithBody request = Unirest.put(cmdbUrl+service.getId()).basicAuth(login, password);
        request.body(jsonInString);
        return request.asString().getBody();
    }

    @RequestMapping(value = "/image", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String updateImage(@RequestBody Image image) throws UnirestException {
        String jsonInString = mapper.writeValue(image);
        HttpRequestWithBody request = Unirest.put(cmdbUrl+image.getId()).basicAuth(login, password);
        request.body(jsonInString);
        return request.asString().getBody();
    }

}
