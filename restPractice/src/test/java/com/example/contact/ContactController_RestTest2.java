package com.example.contact;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ContactController_RestTest2 {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getAllContactsReturnsOkWithListOfContacts_3() throws Exception {
        ResponseEntity<List<Contact>> entity =
                restTemplate.exchange("http://localhost:8080/contacts?page=1&size=4", HttpMethod.GET,null, new ParameterizedTypeReference<List<Contact>>(){});

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        List<Contact> contactList = entity.getBody();
        assertEquals(contactList.get(0).getName(),"123");
        assertThat(contactList.get(0).getName()).isEqualTo("123");
    }


}
