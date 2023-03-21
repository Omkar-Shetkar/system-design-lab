package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Data {

    private final String key;
    private final String value;

    @JsonCreator
    public Data(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
