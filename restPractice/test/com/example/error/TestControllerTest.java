package com.example.error;

import com.example.contact.Contact;
import com.example.contact.ContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TestControllerTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    @Rollback(value = false)
    void insertUser(){
        Contact build = Contact.builder().name("222").tel("123").address("123").build();
        Contact savedContact = contactRepository.save(build);
        System.out.println("savedContact = " + savedContact);
    }
}