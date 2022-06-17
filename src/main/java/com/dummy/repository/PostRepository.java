package com.dummy.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dummy.entity.Post;
import com.dummy.entity.UserPreview;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, String> {

	List<Post> findByOwner(UserPreview userPreview);

	
}
