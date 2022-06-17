package com.dummy.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dummy.entity.Users;

@Repository
public interface UserRepository extends PagingAndSortingRepository<Users, String> {

	
}
