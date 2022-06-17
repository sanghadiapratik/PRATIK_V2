package com.dummy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dummy.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

	
}
