package com.osmos.intercom.controller.Stream;

import com.osmos.intercom.contant.ApplicationConstants;
import com.osmos.intercom.controller.ResponseInterceptor;
import com.osmos.intercom.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StreamsController {

    @Autowired
    StreamService streamService;


    @GetMapping("/Stream/{streamId}")
    public ResponseEntity<String> getStreamDataByQuery1(@PathVariable String streamId,
                                                        @RequestHeader(value = ApplicationConstants.accessToken, required = true) String authorization,
                                                        @RequestHeader(value = ApplicationConstants.Accept) String accept) {

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setBearerAuth(authorization);
        requestHeaders.setAccept(MediaType.parseMediaTypes(accept));
        return ResponseInterceptor.prepareResponseEntity(
                streamService.getStreamObjectPaylod1(streamId, requestHeaders));


    }
}

