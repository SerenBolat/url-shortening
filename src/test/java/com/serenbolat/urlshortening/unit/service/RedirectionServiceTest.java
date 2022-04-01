package com.serenbolat.urlshortening.unit.service;

import com.serenbolat.urlshortening.dao.UrlRepository;
import com.serenbolat.urlshortening.dto.ServiceResponse;
import com.serenbolat.urlshortening.entitiy.UrlEntity;
import com.serenbolat.urlshortening.service.RedirectionServiceImpl;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
public class RedirectionServiceTest {

    @Mock
    private UrlRepository repository;
    @InjectMocks
    private RedirectionServiceImpl service;
    private ServiceResponse response;
    private UrlEntity urlEntity;
    private EasyRandom generator = new EasyRandom();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        response = generator.nextObject(ServiceResponse.class);
        response.setError(null);
        urlEntity = generator.nextObject(UrlEntity.class);
    }

    @Test
    public void testRedirectUrl(){
        Mockito.when(repository.findByShortUrl("123")).thenReturn(urlEntity);
        ServiceResponse serviceResponse = service.redirectUrl("test");
        assertThat(serviceResponse).isNotNull();

    }
}
