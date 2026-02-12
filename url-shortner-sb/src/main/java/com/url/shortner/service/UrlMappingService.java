package com.url.shortner.service;

import com.url.shortner.dto.UrlMappingDto;
import com.url.shortner.models.UrlMapping;
import com.url.shortner.models.User;
import com.url.shortner.repository.UrlMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class UrlMappingService {

    private UrlMappingRepository urlMappingRepository;

    public UrlMappingDto createShortUrl(String originalUrl, User user) {
        String shortUrl=generateShortUrl();
        UrlMapping urlMapping=new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setUser(user);
        urlMapping.setCreatedDate(LocalDateTime.now());
        UrlMapping savedUrlMapping=urlMappingRepository.save(urlMapping);
        return convertToDto(savedUrlMapping);
    }

    private UrlMappingDto convertToDto(UrlMapping urlMapping){
        UrlMappingDto urlMappingDto=new UrlMappingDto();
        urlMappingDto.setId(urlMapping.getId());
        urlMappingDto.setShortUrl(urlMapping.getShortUrl());
        urlMappingDto.setOriginalUrl(urlMapping.getOriginalUrl());
        urlMappingDto.setUsername(urlMapping.getUser().getUsername());
        urlMappingDto.setClickCount(urlMapping.getClickCount());
        urlMappingDto.setCreatedDate(urlMapping.getCreatedDate());
        return urlMappingDto;
    }



    private String generateShortUrl() {
        Random random=new Random();
        String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortUrl=new StringBuilder(8);
        for(int i =0 ;i<8;i++){
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }
}
