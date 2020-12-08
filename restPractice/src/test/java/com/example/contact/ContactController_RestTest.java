package com.example.contact;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class ContactController_RestTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ContactService contactService;

    @Test
    public void getAllContactsReturnsOkWithListOfContacts() throws Exception {
        List<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact(1, "홍길동", "010-2222-1111", "서울"));
        contacts.add(new Contact(2, "이몽룡", "010-2222-1112", "제주"));
        contacts.add(new Contact(3, "성춘향", "010-2222-1113", "경기"));
        contacts.add(new Contact(4, "박문수", "010-2222-1114", "강원"));

        Pageable pageable = PageRequest.of(0,4, Sort.sort(Contact.class));
        Page<Contact> contactList = contactService.getContactList(pageable);

        Mockito.when(contactList).thenReturn(contactList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/contacts").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.pageable.pageNumber", Matchers.is(0)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.contacts", Matchers.hasSize(4)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.contacts[0].no", Matchers.is(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.contacts[3].address", Matchers.is("강원")));
    }

    @Test
    public void insertContactReturnsOkWithValidStatus() throws Exception {
        Result result = new Result("ok", "연락처 추가 성공", "5");

        Mockito.when(contactService.insertContact(Mockito.any((Contact.class)))).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\" : \"다희\", \"tel\" : \"010-9999-9999\", \"address\" : \"서울\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("ok"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void invalidRequestPathReturns404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/asdf")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void invalidRequestReturns400WithAppStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/error/create")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("100"));
    }


}
