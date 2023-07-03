package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortUrl(@RequestBody String longUrl) {

        // Logic to generate a short URL
        String shortUrl = urlShortenerService.generateShortUrl(longUrl);

        // Store the mapping between short URL and long URL

        return new ResponseEntity<>(shortUrl, HttpStatus.CREATED);
    }

}
