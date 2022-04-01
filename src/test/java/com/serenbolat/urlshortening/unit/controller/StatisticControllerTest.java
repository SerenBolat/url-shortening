package com.serenbolat.urlshortening.unit.controller;

import com.serenbolat.urlshortening.controller.StatisticController;
import com.serenbolat.urlshortening.dto.ServiceResponse;
import com.serenbolat.urlshortening.service.StatisticService;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StatisticControllerTest {

    @Mock
    private StatisticService service;
    @InjectMocks
    private StatisticController controller;
    private ServiceResponse response;
    private EasyRandom generator = new EasyRandom();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        response = generator.nextObject(ServiceResponse.class);
        response.setError(null);
    }

    @Test
    public void testStatistics(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        Map<String, Long> statistic = new HashMap<>();
        statistic.put("test",1L);

        ResponseEntity<Map> response = ResponseEntity
                .status(HttpStatus.OK)
                .body(statistic);
        Mockito.when(service.getStatistics("test",request)).thenReturn(response);
        final ResponseEntity<?> serviceResponse = controller.getStatistic("test",request);

        assertThat(serviceResponse).isNotNull();

    }
}
