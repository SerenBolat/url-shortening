package com.serenbolat.urlshortening.unit.controller;

import com.serenbolat.urlshortening.controller.UrlShortenerController;
import com.serenbolat.urlshortening.dto.RequestUrlDto;
import com.serenbolat.urlshortening.dto.ServiceResponse;
import com.serenbolat.urlshortening.service.UrlShorteningService;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class UrlEntityShortenerControllerTest {

    @Mock
    private UrlShorteningService service;
    @InjectMocks
    private UrlShortenerController controller;
    private ServiceResponse response;
    private RequestUrlDto requestUrlDto;
    private EasyRandom generator = new EasyRandom();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        response = generator.nextObject(ServiceResponse.class);
        response.setError(null);
        requestUrlDto = generator.nextObject(RequestUrlDto.class);
    }

    @Test
    public void testRedirectUrl() {
        MockHttpServletRequest request = new MockHttpServletRequest();

        Mockito.when(service.generateShortLink(requestUrlDto,request)).thenReturn(response);
        final ResponseEntity<?> serviceResponse = controller.generateShortLink(requestUrlDto,request);

        assertThat(serviceResponse).isNotNull();

    }
}
