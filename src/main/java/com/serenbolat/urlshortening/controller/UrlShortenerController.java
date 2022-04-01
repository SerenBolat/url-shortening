package com.serenbolat.urlshortening.controller;

import com.serenbolat.urlshortening.dto.*;
import com.serenbolat.urlshortening.service.UrlShorteningService;
import com.serenbolat.urlshortening.service.UrlShorteningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class UrlShortenerController {

    @Autowired
    private UrlShorteningService shortenerService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateShortLink(@RequestBody RequestUrlDto shortRequestDTO, HttpServletRequest request) {

        ServiceResponse serviceResponse = shortenerService.generateShortLink(shortRequestDTO,request);

        if(serviceResponse.getError()!=null){
            ResponseErrorDto responseErrorDto = new ResponseErrorDto();
            responseErrorDto.setStatus("Failed");
            responseErrorDto.setError(serviceResponse.getError());
            return new ResponseEntity<ResponseErrorDto>(responseErrorDto, HttpStatus.BAD_REQUEST);
        }

        ResponseUrlDto responseUrlDto = new ResponseUrlDto();
        responseUrlDto.setShortUrl(serviceResponse.getShortUrl());

        return new ResponseEntity<ResponseUrlDto>(responseUrlDto, HttpStatus.OK);
    }
    @PostMapping("/customGenerate")
    public ResponseEntity<?> generateCustomShort(@RequestBody CustomShortRequestDto customShortRequestDto, HttpServletRequest request) throws Exception {

        ServiceResponse serviceResponse = shortenerService.generateCustomShortUrl(customShortRequestDto,request);

        if(serviceResponse.getError()!=null){
            ResponseErrorDto responseErrorDto = new ResponseErrorDto();
            responseErrorDto.setStatus("Failed");
            responseErrorDto.setError(serviceResponse.getError());
            return new ResponseEntity<ResponseErrorDto>(responseErrorDto, HttpStatus.BAD_REQUEST);
        }

        ResponseUrlDto responseUrlDto = new ResponseUrlDto();
        responseUrlDto.setShortUrl(serviceResponse.getShortUrl());

        return new ResponseEntity<ResponseUrlDto>(responseUrlDto, HttpStatus.OK);
    }

}
