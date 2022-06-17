package com.dummy.dao;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentCreate {

	@Size(min=2, max=1000, message="Message shold be 2 to 1000 char.")
	private String message;
	private String owner;
	private String post;
	
	
}
