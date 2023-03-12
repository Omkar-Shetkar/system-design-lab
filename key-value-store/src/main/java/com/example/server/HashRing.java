package com.example.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class HashRing {

    private @Autowired ServerConfig config;

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
}
