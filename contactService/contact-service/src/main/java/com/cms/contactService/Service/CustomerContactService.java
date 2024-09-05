package com.cms.contactService.Service;

import com.cms.contactService.Entity.CustomerContact;
import com.cms.contactService.Repository.CustomerContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

@Service
public class CustomerContactService {

    @Autowired
    private RestTemplate restTemplate;

    private CustomerContactRepo customerContactRepo;

    private final String dbServiceUrl = "http://localhost:8080";

    public CustomerContact createContact(CustomerContact customerContact) {
        return restTemplate.postForObject(dbServiceUrl + "/createContact", customerContact, CustomerContact.class);
    }

    public void updateContact(Long id, CustomerContact customerContact) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        CustomerContact contact = restTemplate.getForObject(dbServiceUrl + "/getContact/" + id, CustomerContact.class);

        if (role.equals("ROLE_ADMIN")) {
            restTemplate.delete(dbServiceUrl + "/deleteContact/" + id);
        } else if (role.equals("ROLE_USER")) {
            if (contact.getName().equals(username)) {
                restTemplate.put(dbServiceUrl + "/updateContact/" + id, customerContact);
            } else {
                throw new SecurityException("You can only modify your own records.");
            }
        }
    }

    public void deleteContact(Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        CustomerContact customerContact = restTemplate.getForObject(dbServiceUrl + "/getContact/" + id, CustomerContact.class);

        if (role.equals("ROLE_ADMIN")) {
            restTemplate.delete(dbServiceUrl + "/deleteContact/" + id);
        } else if (role.equals("ROLE_USER")) {
            if (customerContact.getName().equals(username)) {
                restTemplate.delete(dbServiceUrl + "/deleteContact/" + id);
            } else {
                throw new SecurityException("You can only delete your own records.");
            }

        }

    }
}
