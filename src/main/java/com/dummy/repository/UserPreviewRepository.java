package com.dummy.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dummy.entity.UserPreview;

@Repository
public interface UserPreviewRepository extends PagingAndSortingRepository<UserPreview, String> {

}
