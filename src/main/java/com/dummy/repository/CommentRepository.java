package com.dummy.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dummy.entity.Comment;
import com.dummy.entity.UserPreview;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, String> {

	List<Comment> findByOwner(UserPreview owner);

	
}
