package com.example.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ContactService {
  @Autowired
  private ContactRepository contactRepository;
  
  public List<Contact> getContactList() {
    return contactRepository.findAll();
  }
  
  public Page<Contact> getContactList(Pageable pageable) {
    return contactRepository.findAll(pageable);
  }

  public Contact getContactOne(Contact c) {
    return contactRepository.findById(c.getNo()).orElseThrow(EntityNotFoundException::new);
  }
  
  public Result insertContact(Contact c) {
    Result result = new Result("ok", "", "");
    try {
      Contact save = contactRepository.save(c);
      result.setMessage("연락처 추가 성공. 추가된 연락처의 일련번호 :" + save.getNo());
      result.setKey(""+save.getNo());
    } catch (Exception ex) {
      result.setStatus("fail");
      result.setMessage(ex.getMessage());
      result.setKey("");
    }
    return result;
  }
  
  public Result updateContact(Contact c) {
    Result result = new Result("ok", "", "");
    try {
      Contact contact = contactRepository.findById(c.getNo()).orElseThrow(EntityNotFoundException::new);
      contact.update(c);
      result.setMessage(c.getName() + " 변경 성공");
      result.setKey(c.getNo()+"");
    } catch (Exception ex) {
      result.setStatus("fail");
      result.setMessage(ex.getMessage());
      result.setKey("");
    }
    return result;
  }
  
  public Result deleteContact(Contact c) {
    Result result = new Result("ok", "", "");
    try {
      contactRepository.deleteById(c.getNo());
      result.setMessage("삭제 성공");
      result.setKey(c.getNo()+"");
    } catch (Exception ex) {
      result.setStatus("fail");
      result.setMessage(ex.getMessage());
      result.setKey("");
    }
    return result;
  }
}