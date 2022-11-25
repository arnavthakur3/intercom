package com.osmos.intercom.service.schema;


import com.osmos.intercom.enums.SchemaObjectEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.HttpClientErrorException;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


@Slf4j
@Service
public class SchemaService {

    @Value("classpath:/input/intercomSchemaFieldConversation.json")
    Resource intercomSchemaFieldConversation;

    @Value("classpath:/input/intercomSchemaFieldTags.json")
    Resource intercomSchemaFieldTags;

    @Value("classpath:/input/intercomSchemaFieldContacts.json")
    Resource intercomSchemaFieldContacts;



    public String getSchemaObjectsMetaData(String streamId) {
        if(SchemaObjectEnum.CONVERSATIONS.getStreamObjectName().equals(streamId)){
            return getJsonResponseFromFile(intercomSchemaFieldConversation);
        } else if(SchemaObjectEnum.TAGS.getStreamObjectName().equals(streamId)){
            return getJsonResponseFromFile(intercomSchemaFieldTags);
        } else if (SchemaObjectEnum.CONTACTS.getStreamObjectName().equals(streamId)){
            return getJsonResponseFromFile(intercomSchemaFieldContacts);
        } throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }



    public String getJsonResponseFromFile(Resource inputResource) {
        String schemaFieldDetails = "";
        try (Reader reader = new InputStreamReader(inputResource.getInputStream())) {
            schemaFieldDetails = FileCopyUtils.copyToString(reader);
            log.debug(" Input Resource of filename {} :: {}", inputResource.getFilename(),
                    schemaFieldDetails);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Error while reading " + inputResource.getFilename() + " from file.", e);
        }
        return schemaFieldDetails;
    }
}


