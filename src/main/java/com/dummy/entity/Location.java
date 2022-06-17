package com.dummy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.dummy.dao.CommentCreate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Location {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Size(min=5, max=100)
	private String street;
	
	@Size(min=2, max=30)
	private String city;
	
	@Size(min=2, max=30)
	private String state;
	
	@Size(min=2, max=30)
	private String country;
	
	
	
}
