package com.cms.contactService.Service;

import com.cms.contactService.Entity.CustomerContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerContactService {

    @Autowired
    private RestTemplate restTemplate;

    private final String dbServiceUrl = "http://localhost:8080";

    public CustomerContact createContact(CustomerContact contact) {
        return restTemplate.postForObject(dbServiceUrl + "/createContact", contact, CustomerContact.class);
    }

    public void updateContact(Long id, CustomerContact contact) {
        restTemplate.put(dbServiceUrl + "/updateContact/" + id, contact);
    }

    public void deleteContact(Long id) {
        restTemplate.delete(dbServiceUrl + "/deleteContact/" + id);
    }

}
