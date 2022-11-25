package com.osmos.intercom.service.create;

import com.osmos.intercom.dto.Payloads;
import com.osmos.intercom.dto.contacts.ContactsPayloads;
import com.osmos.intercom.dto.tags.Tagspayloads;
import com.osmos.intercom.enums.SchemaObjectEnum;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Slf4j
@Service
public class CreateStreamService {

    @Value("${intercom.conversations.auth.baseurl}")
    private String conversationsBaseUrl;

    @Value("${intercom.tags.auth.baseurl}")
    private String TagsBaseUrl;

    @Value("${intercom.contacts.auth.baseurl}")
    private String ContactBaseUrl;


    @Autowired
    private RestTemplate restTemplate;


//    public String getSchemaObjectsMetaData(String streamId,String authorization, String accept, String contentType, Payloads conversation, Tagspayloads tags,ContactsPayloads contact) {
//        if(SchemaObjectEnum.CONVERSATIONS.getStreamObjectName().equals(streamId)){
//            return postStreamObject(authorization, accept, contentType, conversation);
//        }
//        else if(SchemaObjectEnum.TAGS.getStreamObjectName().equals(streamId)){
//            return createTag(authorization, accept, contentType, tags);
//        }
//        else if (SchemaObjectEnum.CONTACTS.getStreamObjectName().equals(streamId)){
//            return createContact(authorization, accept, contentType, contact);
//        } throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
//    }

    public Object getSchemaObjectsMetaData(String authorization, String accept, String contentType, Payloads conversation) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authorization);
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.setAccept(MediaType.parseMediaTypes(accept));
        System.out.println(conversation.getPayloads());
        JSONObject jsonObject = new JSONObject(conversation.getPayloads().get(0));
        String test = jsonObject.toString();
        System.out.println(jsonObject);
//        String json = null;
//        try {
//            json = new ObjectMapper().writeValueAsString(jsonObject);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        HttpEntity entity = new HttpEntity(test, headers);
        Object body = restTemplate.exchange(conversationsBaseUrl, HttpMethod.POST, entity, Object.class).getBody();

//        JSONObject responseJSon = new JSONObject();
//        responseJSon.put("status","completed");
//        responseJSon.put("statusMessage",body);
//        body = responseJSon;

        System.out.println("Converstion_Body: " + body);
//        return responseJSon;
        return body;

    }

    public Object createTag(String authorization, String accept, String contentType, Tagspayloads tags) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authorization);
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.setAccept(MediaType.parseMediaTypes(accept));
        System.out.println(tags.getPayloads());
        JSONObject jsonObject = new JSONObject(tags.getPayloads().get(0));
        String test = jsonObject.toString();
        System.out.println(jsonObject);

        HttpEntity entity = new HttpEntity(test, headers);
        Object body = restTemplate.exchange(TagsBaseUrl, HttpMethod.POST, entity, Object.class).getBody();
        System.out.println("Tag: " + body);
        return body;
    }


    public Object createContact(String authorization,String accept, String contentType, ContactsPayloads contact) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authorization);
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.setAccept(MediaType.parseMediaTypes(accept));
        System.out.println(contact.getPayloads());
        JSONObject jsonObject = new JSONObject(contact.getPayloads().get(0));
        String test = jsonObject.toString();
        System.out.println(jsonObject);

        HttpEntity entity = new HttpEntity(test, headers);
        Object body = restTemplate.exchange(ContactBaseUrl, HttpMethod.POST, entity, Object.class).getBody();
        System.out.println("Tag: " + body);
        return body;
    }
}

