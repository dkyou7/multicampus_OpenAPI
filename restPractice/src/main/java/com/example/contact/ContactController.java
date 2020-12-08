package com.example.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;



    @GetMapping()
    public @ResponseBody Page getContactList(Pageable pageable) {
        return contactService.getContactList(pageable);
    }

    @PostMapping()
    public @ResponseBody Result insertContact(@RequestBody Contact c){
        return contactService.insertContact(c);
    }

    @PutMapping("{no}")
    public @ResponseBody Result updateContact(@PathVariable("no") int no, @RequestBody Contact c){
        c.setNo(no);
        return contactService.updateContact(c);
    }

    @DeleteMapping()
    public @ResponseBody Result deleteContact(@RequestBody Contact c){
        return contactService.deleteContact(c);
    }
}
