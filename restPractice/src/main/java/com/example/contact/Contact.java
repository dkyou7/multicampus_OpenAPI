package com.example.contact;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "contact")
public class Contact {
	
	@JacksonXmlProperty(isAttribute = true)
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	private String name;
	private String tel;
	private String address;

	public void update(Contact c) {
		this.no = c.getNo();
		this.name = c.getName();
		this.tel = c.getTel();
		this.address = c.getAddress();
	}
}