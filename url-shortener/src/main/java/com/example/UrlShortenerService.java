package com.example;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UrlShortenerService {

    AtomicLong uniqueId = new AtomicLong(0);

    public String generateShortUrl(String longUrl) {
        String uuid = String.valueOf(uniqueId.incrementAndGet());
        byte[] encode = Base64.getEncoder().encode(uuid.getBytes(StandardCharsets.UTF_8));
        return new String(encode);
    }
}
