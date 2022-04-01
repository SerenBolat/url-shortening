package com.serenbolat.urlshortening.service;

import com.serenbolat.urlshortening.dto.CustomShortRequestDto;
import com.serenbolat.urlshortening.dto.RequestUrlDto;
import com.serenbolat.urlshortening.dto.ResponseUrlDto;
import com.serenbolat.urlshortening.dto.ServiceResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface UrlShorteningService {

    ServiceResponse generateShortLink(RequestUrlDto urlDto, HttpServletRequest request);

    ServiceResponse generateCustomShortUrl(CustomShortRequestDto urlDto, HttpServletRequest request);
}
