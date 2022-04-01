package com.serenbolat.urlshortening.service;

import com.serenbolat.urlshortening.dao.UrlRepository;
import com.serenbolat.urlshortening.dto.ServiceResponse;
import com.serenbolat.urlshortening.entitiy.UrlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedirectionServiceImpl implements RedirectionService{
    @Autowired
    private UrlRepository urlDao;


    @Override
    public ServiceResponse redirectUrl(String shortUrl) {
        UrlEntity url = getOriginalUrl(shortUrl);
        ServiceResponse serviceResponse = new ServiceResponse();
        if(url==null){
            serviceResponse.setError("URL not exist");
            return serviceResponse;
        }
        incrementVisits(url);
        serviceResponse.setOriginalUrl(url.getOriginalUrl());
        return serviceResponse;
    }


    public UrlEntity getOriginalUrl(String url) {
        return urlDao.findByShortUrl(url);
    }

    private UrlEntity incrementVisits(UrlEntity url) {
        url.setVisits(url.getVisits()+1);
        return urlDao.save(url);
    }
}
