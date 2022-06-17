package com.dummy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

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
public class Comment {

	@Id
	@Column(length = 50)
	//@GeneratedValue(generator = "uuid")
	//@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@ManyToOne
    @JoinColumn(name = "owner")
	private UserPreview owner;
	 
	
	@Size(min=2, max=1000, message="Message shold be 2 to 1000 char.")
	private String message;
	private String post;
	private String publishDate;
	
	
}
