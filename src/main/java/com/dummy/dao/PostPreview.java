package com.dummy.dao;

import java.util.Arrays;

import com.dummy.entity.UserPreview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostPreview {

	private String id;
	private String text;
	private String image;
	private int likes;
	private String[] tags;
	private String publishDate;
	private UserPreview owner;

}
