package com.example.contact;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@JacksonXmlRootElement(localName = "result")
public class Result {
	private String status;
	private String message;
	private String key;
}