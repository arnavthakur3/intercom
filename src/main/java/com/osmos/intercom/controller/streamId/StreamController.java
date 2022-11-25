package com.osmos.intercom.controller.streamId;

import com.osmos.intercom.contant.ApplicationConstants;
import com.osmos.intercom.contant.StreamConstants;
import com.osmos.intercom.controller.ResponseInterceptor;
import com.osmos.intercom.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class StreamController {

    @Autowired
    private StreamService streamService;




    @GetMapping("/Stream/{streamId}/{Id}")
    public ResponseEntity<String> getStreamDataByQuery(
            @RequestHeader(value = ApplicationConstants.accessToken, required = true) String authorization,
            @RequestHeader(value = ApplicationConstants.Accept, required = true) String accept,
            @RequestHeader(value = ApplicationConstants.Accept, required = true) String contentType,
            @PathVariable String streamId,
            @PathVariable String Id,
            @RequestHeader(value = StreamConstants.PAGINATION_META_DATA, required = false) String paginationMetaData) {


        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setBearerAuth(authorization);
        requestHeaders.setAccept(MediaType.parseMediaTypes(accept));
        requestHeaders.setContentType(MediaType.parseMediaType(contentType));
        HashMap<String, String> requestParams = new HashMap<>();
        return ResponseInterceptor.prepareResponseEntity(
                streamService.getStreamObjectPaylod(requestHeaders, requestParams, streamId, Id));
    }

}
