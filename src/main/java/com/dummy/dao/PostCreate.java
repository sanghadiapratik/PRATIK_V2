package com.dummy.dao;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostCreate {

	private String text;
	private String image;
	private int likes;
	private String[] tags;
	private String owner;

	
}
