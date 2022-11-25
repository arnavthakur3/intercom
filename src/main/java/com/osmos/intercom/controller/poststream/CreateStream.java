package com.osmos.intercom.controller.poststream;

import com.osmos.intercom.contant.ApplicationConstants;
import com.osmos.intercom.controller.ResponseInterceptor;
import com.osmos.intercom.dto.Payloads;
import com.osmos.intercom.dto.contacts.ContactsPayloads;
import com.osmos.intercom.dto.tags.Tagspayloads;
import com.osmos.intercom.service.create.CreateStreamService;
import com.osmos.intercom.service.create.tags.CreateStreamTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CreateStream {

    @Autowired
    CreateStreamService createStreamService;




    @PostMapping("/conversations")
    public Object postStreamData(
            @RequestHeader(value = ApplicationConstants.accessToken, required = true) String authorization,
            @RequestHeader(value = ApplicationConstants.Accept, required = true) String accept,
            @RequestHeader(value = ApplicationConstants.Content_Type, required = true) String contentType,
            @RequestBody Payloads conversation) {
        return ResponseInterceptor.prepareResponseEntity(
                createStreamService.getSchemaObjectsMetaData( authorization, accept, contentType, conversation));
    }


    @PostMapping("/tags")
    public Object postStreamData(
            @RequestHeader(value = ApplicationConstants.accessToken,
                    required = true) String authorization,
            @RequestHeader(value = ApplicationConstants.Accept,
                    required = true) String accept,
            @RequestHeader(value = ApplicationConstants.Accept, required = true) String contentType,
            @RequestBody Tagspayloads tags) {
        return ResponseInterceptor.prepareResponseEntity( createStreamService.createTag(authorization, contentType, accept, tags));
    }


    @PostMapping("/contacts")
    public Object postStreamData(
            @RequestHeader(value = ApplicationConstants.accessToken,
                    required = true) String authorization,
            @RequestHeader(value = ApplicationConstants.Accept,
                    required = true) String accept,
            @RequestHeader(value = ApplicationConstants.Accept, required = true) String contentType,
            @RequestBody ContactsPayloads contact) {
        return ResponseInterceptor.prepareResponseEntity( createStreamService.createContact(authorization, contentType, accept, contact));
    }
}
