package com.serenbolat.urlshortening.service;

import com.serenbolat.urlshortening.dao.UrlRepository;
import com.serenbolat.urlshortening.entitiy.UrlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class StatisticServiceImpl implements StatisticService{

    @Autowired
    private UrlRepository urlDao;

    public ResponseEntity<Map> getStatistics(String shortUrl, HttpServletRequest request) {
        if (!isShortUrlExist(shortUrl)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(extractStatistic(shortUrl));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    private boolean isShortUrlExist(String shortUrl) {

        return urlDao.findByShortUrl(shortUrl) == null;
    }
    public UrlEntity getUrl(String url) {
        return urlDao.findByShortUrl(url);
    }
    private Map<String, Long> extractStatistic(String shortUrl) {
        Map<String, Long> statistic = new HashMap<>();
        UrlEntity urlVisit = getUrl(shortUrl);
        statistic.put(urlVisit.getShortUrl(),urlVisit.getVisits());

        return statistic;
    }
}
