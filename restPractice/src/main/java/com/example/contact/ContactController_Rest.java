package com.example.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController_Rest {

    @Autowired
    private ContactService contactService;



    @GetMapping()
    public @ResponseBody Page getContactList_Rest(Pageable pageable) {
        return contactService.getContactList(pageable);
    }

    @PostMapping()
    public @ResponseBody Result insertContact_Rest(@RequestBody Contact c){
        return contactService.insertContact(c);
    }

    @PutMapping("{no}")
    public @ResponseBody Result updateContact_Rest(@PathVariable("no") int no, @RequestBody Contact c){
        c.setNo(no);
        return contactService.updateContact(c);
    }

    @DeleteMapping()
    public @ResponseBody Result deleteContact_Rest(@RequestBody Contact c){
        return contactService.deleteContact(c);
    }
}
