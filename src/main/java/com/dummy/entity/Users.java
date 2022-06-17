package com.dummy.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@Table(name="users")
public class Users{

	@Id
	@Column(length = 100)
	//@GeneratedValue(generator = "uuid")
	//@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@NotNull
	@Pattern(regexp = "mr|ms|mrs|miss|dr|'' ", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String title;
	
	@Size(min=2, max=50)
	private String firstName;
	
	@Size(min=2, max=50)
	private String lastName;
	
	@NotNull
	@Pattern(regexp = "male|other|female|'' ", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String gender;
	
	@Email
	private String email;
	
	private String dateOfBirth;
	
	private String registerDate;
	
	private String phone;
	
	private String picture;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="location")
	private Location location;

	
}
