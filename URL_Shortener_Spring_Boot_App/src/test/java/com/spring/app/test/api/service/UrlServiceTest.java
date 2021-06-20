package com.spring.app.test.api.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.spring.app.api.dto.UrlApiModel;
import com.spring.app.api.entity.UrlApiEntity;
import com.spring.app.api.repository.UrlApiRepository;
import com.spring.app.api.service.ServiceConversionLogic;
import com.spring.app.api.service.UrlApiService;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

	@Mock
	UrlApiRepository mockUrlRepository;

	@Mock
	ServiceConversionLogic mockBaseConversion;

	@InjectMocks
	UrlApiService urlApiService;

	private static String testUrl =  "https://leetcode.com/problemset/all/";
	
	@Test
	public void convertLongUrlTest() {
		var url = setUrl();
		when(mockUrlRepository.save(any(UrlApiEntity.class))).thenReturn(url);
		when(mockBaseConversion.encode(url.getId())).thenReturn("c");
		var urlRequest = new UrlApiModel();
		urlRequest.setLongUrl(testUrl);

		assertEquals("c", urlApiService.convertLongToShortUrl(urlRequest));
	}

	@Test
	public void getlongUrlTest() {
		when(mockBaseConversion.decode("h")).thenReturn((long) 7);
		var url = setUrl();
		when(mockUrlRepository.findById((long) 7)).thenReturn(java.util.Optional.of(url));
		assertEquals(testUrl, urlApiService.getLongUrl("c"));

	}

	private UrlApiEntity setUrl() {
		var url = new UrlApiEntity();
		url.setLongUrl(testUrl);
		url.setCreatedDate(new Date());
		url.setId(6);
		return url;
	}
}
