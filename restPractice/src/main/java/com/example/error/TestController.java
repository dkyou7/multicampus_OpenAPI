package com.example.error;

import com.example.contact.Contact;
import com.example.contact.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value="/error")
public class TestController {

  @Autowired
  private ContactRepository contactRepository;
  
  @GetMapping("create")
  public Contact getTestContactOne(
      @RequestParam(value="name", required=false) String name) {
    try{
      Contact byName = contactRepository.findByName(name).orElseThrow(EntityNotFoundException::new);
      System.out.println("byName = " + byName);
      return byName;
    }catch (Exception e){
      throw new ApiException("name 파라미터는 비워둘 수 없습니다.");
    }
  }

  /**
   * 예외처리
   */
  @ExceptionHandler(value= { ApiException.class } )
  public ResponseEntity<ApiErrorInfo> handleCustomException(ApiException e) {
    ApiErrorInfo error = new ApiErrorInfo("@ExceptionHandler : " + e.getMessage(), e.getStatus());
    ResponseEntity<ApiErrorInfo> entity = new ResponseEntity<ApiErrorInfo>(error, HttpStatus.BAD_REQUEST);
    return entity;
  }
}