package com.osmos.intercom.dto;

import com.osmos.intercom.dto.tags.Tags;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Payloads {
    private List<Conversation> payloads;

}
