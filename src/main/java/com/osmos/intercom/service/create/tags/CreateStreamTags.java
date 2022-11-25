package com.osmos.intercom.service.create.tags;

import com.osmos.intercom.dto.tags.Tagspayloads;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class CreateStreamTags {



    @Value("${intercom.tags.auth.baseurl}")
    private String TagsBaseUrl;


    @Autowired
    private RestTemplate restTemplate;


//    public Object createTag(String authorization, Tagspayloads tags, String accept, String contentType ) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(authorization);
//        headers.setContentType(MediaType.parseMediaType(contentType));
//        headers.setAccept(MediaType.parseMediaTypes(accept));
//        System.out.println(tags.getTags());
//        JSONObject jsonObject = new JSONObject(tags.getTags().get(0));
//        String test = jsonObject.toString();
//        System.out.println(jsonObject);
//
//        HttpEntity entity = new HttpEntity(test, headers);
//        Object body = restTemplate.exchange(TagsBaseUrl, HttpMethod.POST, entity, Object.class);
//        System.out.println("Tag: " + body);
//        return body;
//    }
}
