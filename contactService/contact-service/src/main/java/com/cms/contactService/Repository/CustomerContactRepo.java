package com.cms.contactService.Repository;

import com.cms.contactService.Entity.CustomerContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerContactRepo extends JpaRepository<CustomerContact, Long> {
}
