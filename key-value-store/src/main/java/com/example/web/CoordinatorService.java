package com.example.web;

import com.example.model.Data;
import com.example.server.HashRing;
import org.springframework.stereotype.Service;

@Service
public class CoordinatorService {

    private final HashRing hashRing;

    public CoordinatorService(HashRing hashRing) {
        this.hashRing = hashRing;
    }

    public void add(Data data) {
        hashRing.addData(data);
    }

    public boolean delete(String key) {
        return hashRing.deleteData(key);
    }
}
