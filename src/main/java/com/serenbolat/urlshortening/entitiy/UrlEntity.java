package com.serenbolat.urlshortening.entitiy;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Getter
@Setter
public class UrlEntity {

    @Id
    @GeneratedValue
    private long id;

    @Lob
    private String originalUrl;
    private String shortUrl;
    private long visits;

    public UrlEntity() {
    }

    public UrlEntity(String originalUrl, String shortUrl, long visits) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.visits = visits;
    }


}
