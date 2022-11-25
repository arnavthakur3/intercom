package com.osmos.intercom.service;

import com.osmos.intercom.client.ExternalHttpClient;
import com.osmos.intercom.enums.SchemaObjectEnum;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;

@Slf4j
@Service
public class StreamService {

    @Autowired
    ExternalHttpClient httpClient;
    private Logger logger = LoggerFactory.getLogger(ExternalHttpClient.class);

    @Value("${intercom.conversations.auth.baseurl}")
    private String conversationsBaseUrl;

    @Value("${intercom.tags.auth.baseurl}")
    private String tagsBaseUrl;

    @Value("${intercom.contacts.auth.baseurl}")
    private String contactsBaseUrl;




/*
    Stream/{streamId}
    List Of All Conversation
    List Of All Tags
    List Of All Contacts

*/

    public String getStreamObjectPaylod1(String streamId, HttpHeaders requestHeaders) {
        String response = "";
        log.info("inside service :  requestHeaders {} , Stream ID{}", requestHeaders, streamId);
        String url = buildObjectUrl1(streamId, requestHeaders);
        String responseDto = getStreamData1(url, requestHeaders);
        return responseDto;
    }


    private String buildObjectUrl1(String streamId, HttpHeaders requestHeaders) {
        String finalUrl1 = "";
        try {
            if(SchemaObjectEnum.CONVERSATIONS.getStreamObjectName().equals(streamId)) {
                return finalUrl1 = conversationsBaseUrl ;
            } else if (SchemaObjectEnum.TAGS.getStreamObjectName().equals(streamId)){
                return finalUrl1 = tagsBaseUrl ;
            } else if (SchemaObjectEnum.CONTACTS.getStreamObjectName().equals(streamId)){
                return finalUrl1 = contactsBaseUrl ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalUrl1;
    }


    private String getStreamData1(String url, HttpHeaders requestHeaders) {
        ResponseEntity<String> responseDto = httpClient.sendGetRequest(url, requestHeaders, MediaType.APPLICATION_JSON, String.class);
        logger.info("response DTO : {}", responseDto);
        return extractStreamObjectFromPayloads(
                responseDto,  requestHeaders);
    }

    private String extractStreamObjectFromPayloads(ResponseEntity<String> response, HttpHeaders requestHeaders) {
        JSONObject payloads = new JSONObject();
        JSONArray dataArray = new JSONArray();
        JSONObject payloadData = new JSONObject(response.getBody());

        dataArray.put(payloadData);
        payloads.put("payload", dataArray);
        return payloads.toString();
    }




/*
    Stream By Id
    Find Conversation By Id
    Find Tags By Id
    Find Contacts By Id
*/




    public String getStreamObjectPaylod(HttpHeaders requestHeaders,
                                        HashMap<String, String> requestparams,
                                        String streamId,
                                        String Id) {
        String response = "";
        log.info("inside service :  requestHeaders{}, requestParams {}, SchemaId{}, StreamId{}", requestHeaders, requestparams, streamId, Id);
        String url = buildObjectUrl(requestHeaders, requestparams, streamId, Id);
        response = getStreamData(url, requestHeaders, requestparams, streamId);
        return response;
    }

    private String buildObjectUrl(
            HttpHeaders requestHeaders,
            HashMap<String, String> requestparams,
            String streamId,
            String objectName) {
        String finalUrl = "";
        try {
            if(SchemaObjectEnum.CONVERSATIONS.getStreamObjectName().equals(streamId)) {
                return finalUrl = conversationsBaseUrl + objectName;
            } else if(SchemaObjectEnum.TAGS.getStreamObjectName().equals(streamId)){
                return finalUrl = tagsBaseUrl + objectName;
            }else if(SchemaObjectEnum.CONTACTS.getStreamObjectName().equals(streamId))
                return finalUrl = conversationsBaseUrl + objectName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalUrl;
    }

    private String getStreamData(String url, HttpHeaders requestHeaders,
                                 HashMap<String, String> requestparams,
                                 String Id) {
        ResponseEntity<String> responseDto =
                httpClient.sendGetRequest1(
                        url, requestparams, requestHeaders, MediaType.APPLICATION_JSON, String.class);
        log.info("response DTO : {}", responseDto);

        return extractStreamObjectFromJsonResponseForV3Object(
                responseDto, Id,  requestHeaders, requestparams);
    }


    private String extractStreamObjectFromJsonResponseForV3Object(ResponseEntity<String> response,
                                                                  String Id,
                                                                  HttpHeaders requestHeaders,
                                                                  HashMap<String, String> requestparams) {
        JSONObject payloads = new JSONObject();
        JSONArray dataArray = new JSONArray();
        JSONObject payloadData = new JSONObject(response.getBody());
        int totalPageNumber = 0;
        int nextPageNumber = 0;
        String paginationMetaData;
        boolean more = false;

        dataArray.put(payloadData);
        payloads.put("payload", dataArray);
        return payloads.toString();
    }
}





