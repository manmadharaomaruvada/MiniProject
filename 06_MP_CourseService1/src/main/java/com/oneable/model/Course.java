package com.oneable.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Course {

	@Id
	@GeneratedValue
	private Integer cid;
	private String cname;
	private Double cfee;
	private String ctype;
	private Double discount;
	private Double gst;
	
}
