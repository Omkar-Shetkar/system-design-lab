package com.example.web;

import com.example.server.HashRing;
import org.springframework.stereotype.Service;

@Service
public class CoordinatorService {

    private final HashRing hashRing;

    public CoordinatorService(HashRing hashRing) {
        this.hashRing = hashRing;
    }

    public void add(String key, String value) {
        hashRing.addData(key, value);
    }
}
