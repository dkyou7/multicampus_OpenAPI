package com.example.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping()
    public String getContactList(Pageable pageable, Model model) {
        Page<Contact> contactList = contactService.getContactList(pageable);
        long count = contactList.get().count();
        System.out.println("count = " + count);
        model.addAttribute("data", contactList);
        return "contacts/contacts";
    }

    @PostMapping("/add")
    public String insertContact(Contact c){
        contactService.insertContact(c);
        return "redirect:/contacts";
    }

    @PostMapping("/update")
    public String  updateContact(Contact c) {
        contactService.updateContact(c);
        return "redirect:/contacts";
    }

    @DeleteMapping()
    public Result deleteContact(Contact c){
        return contactService.deleteContact(c);
    }
}
