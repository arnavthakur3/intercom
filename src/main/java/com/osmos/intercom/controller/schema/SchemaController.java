package com.osmos.intercom.controller.schema;

import com.osmos.intercom.controller.ResponseInterceptor;
import com.osmos.intercom.service.schema.SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchemaController {

    @Autowired
    SchemaService schemaService;

    @GetMapping("/stream/{streamId}/schema")
    public ResponseEntity<String> getObjectFields(@PathVariable String streamId) {
        return getobjectsMetadata(streamId);
    }

    private ResponseEntity<String> getobjectsMetadata(String streamId) {
        return ResponseInterceptor.prepareResponseEntity(schemaService.getSchemaObjectsMetaData(streamId));
    }
}
