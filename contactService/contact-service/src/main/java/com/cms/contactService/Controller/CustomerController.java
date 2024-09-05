package com.cms.contactService.Controller;

import com.cms.contactService.Entity.CustomerContact;
import com.cms.contactService.Service.CustomerContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {
    @Autowired
    private CustomerContactService customerContactService;

    @PostMapping("/customer/contact")
    public ResponseEntity<CustomerContact> createCustomerContact(@RequestBody CustomerContact customerContact) {
        CustomerContact createdContact = customerContactService.createContact(customerContact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    @PutMapping("/customer/contact/{id}")
    public ResponseEntity<Void> updateCustomerContact(@PathVariable Long id, @RequestBody CustomerContact customerContact) {
        customerContactService.updateContact(id, customerContact);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/customer/contact/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        customerContactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
