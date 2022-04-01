package com.serenbolat.urlshortening.dao;

import com.serenbolat.urlshortening.entitiy.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    public UrlEntity findByShortUrl(String shortUrl);

    public UrlEntity findByOriginalUrl(String originalUrl);

}
