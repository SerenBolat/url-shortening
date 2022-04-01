package com.serenbolat.urlshortening.service;

import com.serenbolat.urlshortening.dao.UrlRepository;
import com.serenbolat.urlshortening.dto.CustomShortRequestDto;
import com.serenbolat.urlshortening.dto.RequestUrlDto;
import com.serenbolat.urlshortening.dto.ServiceResponse;
import com.serenbolat.urlshortening.entitiy.UrlEntity;
import com.serenbolat.urlshortening.utility.AlphanumericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URL;

@Component
public class UrlShorteningServiceImpl implements UrlShorteningService {

    @Autowired
    private UrlRepository urlDao;

    public ServiceResponse generateShortLink(RequestUrlDto requestUrlDto, HttpServletRequest request) {
        ServiceResponse serviceResponse = new ServiceResponse();
        // The input URL format must be valid
        if (!isUrlValid(requestUrlDto.getUrl())) {
            serviceResponse.setError("The input URL format must be valid");
        }

        UrlEntity urlControl = isShortUrlExist(requestUrlDto.getUrl());
        if(urlControl == null) {
            UrlEntity url = new UrlEntity(
                    requestUrlDto.getUrl(),
                    generateUniqueShortUrl(6),
                    0L
            );
            UrlEntity savedUrlEntity = urlDao.save(url);
            serviceResponse.setShortUrl(getBaseUrl(request)+ savedUrlEntity.getShortUrl());
        }else{
            serviceResponse.setShortUrl(getBaseUrl(request)+urlControl.getShortUrl());
        }

        return serviceResponse;
    }

    @Override
    public ServiceResponse generateCustomShortUrl(CustomShortRequestDto requestUrlDto, HttpServletRequest request) {
        ServiceResponse serviceResponse = new ServiceResponse();
        // The input URL format must be valid
        if (!isUrlValid(requestUrlDto.getOriginalUrl()) ) {
            serviceResponse.setError("The input URL format must be valid");
        }
        UrlEntity urlControl = isShortUrlExist(requestUrlDto.getOriginalUrl());
        if(urlControl == null) {
            UrlEntity url = new UrlEntity(
                    requestUrlDto.getOriginalUrl(),
                    requestUrlDto.getShortUrl(),
                    0L
            );
            urlDao.save(url);

            serviceResponse.setShortUrl(getBaseUrl(request)+url.getShortUrl());
        }else{
            urlControl.setShortUrl(requestUrlDto.getShortUrl());
            urlDao.save(urlControl  );
            serviceResponse.setShortUrl(getBaseUrl(request)+urlControl.getShortUrl());
        }
        serviceResponse.setOriginalUrl(requestUrlDto.getOriginalUrl());
        return serviceResponse;

    }

    private String  generateUniqueShortUrl(int length) {
        String shortUrl = AlphanumericGenerator.generateAlphaNumericString(length);
        while (!isShortUrlUnique(shortUrl)) {
            shortUrl = AlphanumericGenerator.generateAlphaNumericString(length);
        }
        return shortUrl;
    }

    private boolean isShortUrlUnique(String shortUrl) {

        return urlDao.findByShortUrl(shortUrl) == null;
    }

    private boolean isUrlValid(String url) {
        try {
            URI uri = new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String getBaseUrl(HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() == 8080) {
            baseUrl = baseUrl + ":" + request.getServerPort() + "/";
        } else {
            baseUrl = baseUrl + "/";
        }
        return baseUrl;
    }

    public UrlEntity isShortUrlExist(String url) {
        return urlDao.findByOriginalUrl(url);
    }


}
