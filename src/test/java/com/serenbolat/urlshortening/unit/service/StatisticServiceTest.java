package com.serenbolat.urlshortening.unit.service;

import com.serenbolat.urlshortening.dao.UrlRepository;
import com.serenbolat.urlshortening.dto.ServiceResponse;
import com.serenbolat.urlshortening.entitiy.UrlEntity;
import com.serenbolat.urlshortening.service.StatisticServiceImpl;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StatisticServiceTest {

    @Mock
    private UrlRepository repository;
    @InjectMocks
    private StatisticServiceImpl service;
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
        MockHttpServletRequest request = new MockHttpServletRequest();
        Mockito.when(repository.findByShortUrl("123")).thenReturn(urlEntity);
        ResponseEntity<Map> serviceResponse = service.getStatistics("test",request);
        assertThat(serviceResponse).isNotNull();

    }
}
