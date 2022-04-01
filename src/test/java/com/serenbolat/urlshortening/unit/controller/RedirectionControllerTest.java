package com.serenbolat.urlshortening.unit.controller;

import com.serenbolat.urlshortening.controller.RedirectionController;
import com.serenbolat.urlshortening.dto.ServiceResponse;
import com.serenbolat.urlshortening.service.RedirectionService;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

public class RedirectionControllerTest {

    @Mock
    private RedirectionService service;
    @InjectMocks
    private RedirectionController controller;
    private ServiceResponse response;
    private EasyRandom generator = new EasyRandom();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        response = generator.nextObject(ServiceResponse.class);
        response.setError(null);
    }

    @Test
    public void testRedirectUrl(){

        Mockito.when(service.redirectUrl("test")).thenReturn(response);
        final ResponseEntity<?> serviceResponse = controller.redirect("test");

        assertThat(serviceResponse).isNotNull();

    }
}
