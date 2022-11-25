package com.osmos.intercom.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Conversation {
    private From from;
    private String body;
}
