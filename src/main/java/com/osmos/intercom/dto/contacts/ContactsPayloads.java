package com.osmos.intercom.dto.contacts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactsPayloads {
    private List<Contacts> payloads;
}
