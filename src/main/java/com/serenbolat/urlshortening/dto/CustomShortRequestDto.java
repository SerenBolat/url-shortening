package com.serenbolat.urlshortening.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomShortRequestDto {

    private String shortUrl;
    private String originalUrl;

}
