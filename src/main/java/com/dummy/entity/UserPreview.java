package com.dummy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
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
public class UserPreview {
	
	@Id
	private String id;
	
	private String title;

	private String firstName;
	
	private String lastName;
	
	private String picture;

	
	
}
