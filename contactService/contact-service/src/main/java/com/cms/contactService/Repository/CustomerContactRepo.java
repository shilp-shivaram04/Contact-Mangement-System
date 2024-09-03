package com.cms.contactService.Repository;

import com.cms.contactService.Entity.CustomerContact;
import org.springframework.data.repository.CrudRepository;

public interface CustomerContactRepo extends CrudRepository<CustomerContact, Long> {
}
