package com.example.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "contacts", path = "contactsRestResource")
public interface ContactRepository_RestResource extends JpaRepository<Contact, Long> {

    @RestResource(path = "findByName",exported = true)
    List<Contact> findByNameContainingOrderByNameDesc(@Param("name") String name);
}