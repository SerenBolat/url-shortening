package com.serenbolat.urlshortening.service;

import com.serenbolat.urlshortening.dto.ServiceResponse;
import org.springframework.stereotype.Service;

@Service
public interface RedirectionService {

    ServiceResponse redirectUrl(String shortUrl);

}
