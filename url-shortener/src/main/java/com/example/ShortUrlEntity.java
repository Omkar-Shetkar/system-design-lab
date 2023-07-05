package com.example;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class ShortUrlEntity {

    @Id
    private Long id;

    private String shortUrl;

    private String longUrl;

    public ShortUrlEntity() {}
}
