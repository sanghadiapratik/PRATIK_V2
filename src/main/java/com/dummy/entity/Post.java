package com.dummy.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Post {

	@Id
	//@GeneratedValue(generator = "uuid")
	//@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(length = 1000)
	private String text;
	private String image;
	private String likes;
	private String link;
	private String[] tags;
	private String publishDate;
	
	@ManyToOne
    @JoinColumn(name = "owner")
	private UserPreview owner;
	
}
