package com.example.error;

import com.example.contact.Contact;
import com.example.contact.ContactRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestControllerTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void insertUser(){
        Contact insertContact = Contact.builder().name("123").tel("123").address("123").build();
        contactRepository.save(insertContact);
    }
}