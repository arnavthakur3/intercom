package com.osmos.intercom.dto.contacts;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Data
@Service
public class Contacts {
    private String role;
    private String email;
    private String phone;
    private String name;
    private String avatar;
    private Timestamp signed_up_at;
    private Timestamp last_seen_at;
    private Integer owner_id;
    private Boolean unsubscribed_from_emails;
    private CustomAttributes custom_attributes;


}
