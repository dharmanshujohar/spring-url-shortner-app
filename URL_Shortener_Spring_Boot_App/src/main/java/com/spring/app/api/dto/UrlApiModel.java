package com.spring.app.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "POST method API")
public class UrlApiModel {

	@ApiModelProperty(required = true, notes = "Given Url gets converted to short URL")
	private String longUrl;

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
}
