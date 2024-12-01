package org.example;

import java.util.HashMap;
import java.util.Map;

public class UserCache {

    private final static Map<String, User> users = new HashMap<>();


    public static User user(String name) {
        return users.computeIfAbsent(name, User::new);
    }


}
