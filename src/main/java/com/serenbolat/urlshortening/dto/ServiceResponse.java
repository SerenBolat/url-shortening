package com.serenbolat.urlshortening.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ServiceResponse {
    private String shortUrl;
    private String originalUrl;
    private String status;
    private String error;
}
