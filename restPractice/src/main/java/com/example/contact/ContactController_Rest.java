package com.example.contact;

import com.example.error.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController_Rest {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping()
    public List<Contact> getContactsAll() {
        return contactRepository.findAll(Sort.by(Sort.Direction.DESC, "no"));
    }
    @GetMapping("{no}")
    public Optional<Contact> getContactOne(@PathVariable("no") long no) {
        return contactRepository.findById(no);
    }
    @PostMapping
    public Contact insertContact(@RequestBody Contact c) {
        return contactRepository.save(c);
    }

    @PutMapping("{no}")
    public Contact updateContact(@RequestBody Contact c, @PathVariable("no") long no) {
        c.setNo(no);
        return contactRepository.save(c);
    }

    @DeleteMapping("{no}")
    public void deleteContact(@PathVariable("no") long no) {
        contactRepository.deleteById(no);
    }
}
