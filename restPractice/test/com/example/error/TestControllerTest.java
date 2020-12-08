package com.example.error;

import com.example.contact.Contact;
import com.example.contact.ContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestControllerTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    @Commit
    void insertUser(){
        Contact build = Contact.builder().name("123").tel("123").address("123").build();
        contactRepository.save(build);
    }
}