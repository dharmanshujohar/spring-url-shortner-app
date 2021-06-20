package com.spring.app.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.app.api.entity.UrlApiEntity;

@Repository
public interface UrlApiRepository extends JpaRepository<UrlApiEntity, Long> {
	
	List<UrlApiEntity> findByLongUrl(String longUrl);
	
	UrlApiEntity findByShortUrl(String longUrl);
	
}
