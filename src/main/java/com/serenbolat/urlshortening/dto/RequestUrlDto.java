package com.serenbolat.urlshortening.dto;

public class RequestUrlDto {

    private String url;

    public RequestUrlDto(String url) {
        this.url = url;
    }

    public RequestUrlDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UrlDto{" +
                "url='" + url + '\'' +
                '}';
    }

}
