package com.spring.app.api.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.api.dto.UrlApiModel;
import com.spring.app.api.service.UrlApiService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/url/shortner/service/v1")
public class UrlApiController {

	private final UrlApiService urlApiService;

	public UrlApiController(UrlApiService urlApiService) {
		this.urlApiService = urlApiService;
	}

	@ApiOperation(value = "Convert new url", notes = "Converts given URL to Short URL")
	@PostMapping("short_url")
	public String convertLongToShortUrl(@RequestBody UrlApiModel request) {
		return urlApiService.convertLongToShortUrl(request);
	}

	@ApiOperation(value = "Redirect", notes = "Finds original url from short url and redirects")
	@GetMapping(value = "{shortUrl}")
	//@Cacheable(value = "mappedUrl", key = "#shortUrl", sync = true)
	public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
		var mappedUrl = urlApiService.getLongUrl(shortUrl);
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(mappedUrl)).build();
	}
}
