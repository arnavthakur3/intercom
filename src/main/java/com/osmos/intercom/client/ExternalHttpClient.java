package com.osmos.intercom.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Component
public class ExternalHttpClient {

  public static final String API_RESPONSE_CODE = "API response code: {}";
  public static final String FINAL_URL = "Final url:  {}";

  @Autowired RestTemplate restTemplate;




  public <T> ResponseEntity<T> sendGetRequest(
          String url,
          HttpHeaders requestHeaders,
          MediaType mediaType,
          Class<T> responseDto) {

    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
    log.info(FINAL_URL, builder.build(false).toUriString());

    HttpEntity entity = new HttpEntity("", requestHeaders);
    ResponseEntity<T> response =
            restTemplate.exchange(builder.build(false).toUriString(), HttpMethod.GET, entity, responseDto );
    log.info(API_RESPONSE_CODE, response.getStatusCode().value());

    return response;
  }




  /* Stream By Id
  Find Conversation By Id
   */

  public <T> ResponseEntity<T> sendGetRequest1(
      String url,
      Map<String, String> requestParams,
      HttpHeaders requestHeaders,
      MediaType mediaType,
      Class<T> responseDto) {

    // Add query params if any from original request
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
    for (Map.Entry<String, String> entry : requestParams.entrySet()) {
      builder.queryParam(entry.getKey(), entry.getValue());
    }
    log.info(FINAL_URL, builder.build(false).toUriString());

    HttpEntity entity = new HttpEntity("", requestHeaders);

    ResponseEntity<T> response =
            restTemplate.exchange(builder.build(false).toUriString(), HttpMethod.GET, entity, responseDto );
    log.info(API_RESPONSE_CODE, response.getStatusCode().value());

    return response;
  }

}
