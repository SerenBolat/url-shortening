package com.serenbolat.urlshortening.controller;

import com.serenbolat.urlshortening.dto.ResponseErrorDto;
import com.serenbolat.urlshortening.dto.ResponseOriginalUrlDto;
import com.serenbolat.urlshortening.dto.ServiceResponse;
import com.serenbolat.urlshortening.service.RedirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RedirectionController {

    @Autowired
    private RedirectionService redirectService;


    @GetMapping("{shortUrl}")
    public ResponseEntity<?> redirect(@PathVariable String shortUrl) {
        ServiceResponse serviceResponse = redirectService.redirectUrl(shortUrl);
        if(serviceResponse.getError() != null){
            ResponseErrorDto responseErrorDto = new ResponseErrorDto();
            responseErrorDto.setStatus(HttpStatus.BAD_REQUEST.toString());
            responseErrorDto.setError(serviceResponse.getError());
            return new ResponseEntity<ResponseErrorDto>(responseErrorDto, HttpStatus.BAD_REQUEST);
        }
        ResponseOriginalUrlDto responseUrlDto = new ResponseOriginalUrlDto();
        responseUrlDto.setOriginalUrl(serviceResponse.getOriginalUrl());
        return new ResponseEntity<ResponseOriginalUrlDto>(responseUrlDto, HttpStatus.OK);
    }

}
