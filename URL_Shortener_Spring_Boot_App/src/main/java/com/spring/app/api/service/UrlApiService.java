package com.spring.app.api.service;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.spring.app.api.dto.UrlApiModel;
import com.spring.app.api.entity.UrlApiEntity;
import com.spring.app.api.repository.UrlApiRepository;

@Service
public class UrlApiService {

	private final UrlApiRepository urlApiRepository;
	private final ServiceConversionLogic serviceConversionLogic;

	public UrlApiService(UrlApiRepository urlApiRepository, ServiceConversionLogic serviceConversionLogic) {
		this.urlApiRepository = urlApiRepository;
		this.serviceConversionLogic = serviceConversionLogic;
	}

	public String convertLongToShortUrl(UrlApiModel request) {
		UrlApiEntity buildUrl = new UrlApiEntity();

		if (urlApiRepository.findByLongUrl(request.getLongUrl()).equals(request.getLongUrl())) {
			return urlApiRepository.findByShortUrl(request.getLongUrl()).getShortUrl();
		} else {
			var entity = buildUrlEntity(request, buildUrl);
			return serviceConversionLogic.encode(entity.getId());
		}
	}

	private UrlApiEntity buildUrlEntity(UrlApiModel request, UrlApiEntity buildUrl) {
		buildUrl.setLongUrl(request.getLongUrl());
		buildUrl.setCreatedDate(new Date());
		var entity = urlApiRepository.save(buildUrl);
		buildUrl.setShortUrl(serviceConversionLogic.encode(entity.getId()));
		urlApiRepository.save(buildUrl);
		return entity;
	}

	public String getLongUrl(String shortUrl) {
		var id = serviceConversionLogic.decode(shortUrl);
		var entity = urlApiRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));
		entity.getId();
		return entity.getLongUrl();
	}
}
