package com.serenbolat.urlshortening.service;

import com.serenbolat.urlshortening.dto.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public interface StatisticService {

    ResponseEntity<Map> getStatistics(String shortUrl, HttpServletRequest request);

}
