package com.example.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class HashRing {

    private @Autowired ServerConfig config;

    private static final Logger LOGGER = LoggerFactory.getLogger(HashRing.class);

    private Node[] nodes = new Node[100];

    public void addNode(Node node) {
        int hash = computeHash(node.getName());
        nodes[hash] = node;
    }

    private int computeHash(String name) {
        return name.chars().sum() % config.getHashSpace();
    }

    public Node removeNode(String name) {
        int hash = computeHash(name);
        Node node = nodes[hash];
        if (node != null) {
            nodes[hash] = null;
            return node;
        }
        return null;
    }

    @Override
    public String toString() {
        return Arrays.stream(nodes).filter(n -> n != null).map(n -> n.getName()).collect(Collectors.joining("-->"));
    }

    public void addData(String key, String value) {
        int hash = computeHash(key);
        Node node = findNode(hash);
        if (node != null) {
            node.put(key, value);
            LOGGER.info("Added key {} into node {}", key, node.getName());
        } else {
            LOGGER.error("Couldn't find appropriate node for key {}", key);
        }
    }

    /**
     * Find node for the given hash in clock-wise direction
     */
    private Node findNode(int hash) {
        if (nodes[hash] != null) {
            return nodes[hash];
        }
        int index = hash < nodes.length - 1 ? hash + 1 : hash;

        while (index != hash) {
            if (nodes[index] != null) {
                return nodes[index];
            } else {
                index = index < nodes.length - 1 ? index + 1 : 0;
            }
        }
        return null;
    }
}
