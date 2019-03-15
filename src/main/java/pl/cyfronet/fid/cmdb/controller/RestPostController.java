package pl.cyfronet.fid.cmdb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.cyfronet.fid.cmdb.bean.object.ImageObject;
import pl.cyfronet.fid.cmdb.bean.object.ServiceObject;
import pl.cyfronet.fid.cmdb.bean.object.SiteObject;

import java.io.IOException;

@Log4j
@RestController
public class RestPostController {

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

    @RequestMapping(value = "/site", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String updateSite(@RequestBody SiteObject site) throws UnirestException {
        String jsonInString = mapper.writeValue(site);
        HttpRequestWithBody request = Unirest.post(cmdbUrl).basicAuth(login, password).header("Content-Type", "application/json");
        request.body(jsonInString);
        HttpResponse<String> response = request.asString();
        return response.getBody();
    }

    @RequestMapping(value = "/service", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String updateService(@RequestBody ServiceObject service) throws UnirestException {
        String jsonInString = mapper.writeValue(service);
        HttpRequestWithBody request = Unirest.post(cmdbUrl).basicAuth(login, password).header("Content-Type", "application/json");
        request.body(jsonInString);
        return request.asString().getBody();
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String updateImage(@RequestBody ImageObject image) throws UnirestException {
        String jsonInString = mapper.writeValue(image);
        HttpRequestWithBody request = Unirest.post(cmdbUrl).basicAuth(login, password).header("Content-Type", "application/json");
        request.body(jsonInString);
        return request.asString().getBody();
    }

}
