package com.example.server;

import java.util.Map;

public class Node {

    private String name;

    private Map<String, String> data;

    public Node(String name, Map<String, String> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getData() {
        return data;
    }

    public String put(String key, String value) {
        return data.put(key, value);
    }
}
