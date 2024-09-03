package com.cms.contactService.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class CustomerContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CustomerId;
    private String Name;
    private Date DateOfBirth;
    private String Address;
    private String City;
    private String PinCode;
    private String ContactNumber;
    private String Email;
}
