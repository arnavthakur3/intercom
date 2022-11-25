package com.osmos.intercom.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String status;
    private String statusMessage;
    private List <FailedRecords> failedRecords;
}
