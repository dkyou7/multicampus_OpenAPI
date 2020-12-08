package com.example.error;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 예외 정보 응답을 위한 domain 클래스
 */
@Data
@NoArgsConstructor @AllArgsConstructor
@JacksonXmlRootElement(localName="restApiError")
public class ApiErrorInfo {
  private String message;
  private String status;
}