package com.example.server;

import com.example.model.Data;
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

    public void addData(Data data) {
        String key = data.getKey();
        String value = data.getValue();
        int hash = computeHash(key);
        int index = findNode(hash);
        if (index != -1) {
            Node node = nodes[index];
            node.put(key, value);
            LOGGER.info("Added key {} into node {}", key, node.getName());
            replicate(key, value, index);
        } else {
            LOGGER.error("Couldn't find appropriate node for key {}", key);
        }
    }

    private void replicate(String key, String value, int fromNodeIndex) {
        for (int i = 0; i < config.getReplication(); i++) {
            int index = fromNodeIndex < nodes.length - 1 ? fromNodeIndex + 1 : 0;
            nodes[index].put(key, value);
            LOGGER.info("Replicated key {} into node {}", key, nodes[index].getName());
            fromNodeIndex = index;
        }
    }

    /**
     * Delete a data item from a node on hash ring
     *
     * @param key - the Key
     * @return - {@code true} if success or else {@code false}
     */
    public boolean deleteData(String key) {
        int hash = computeHash(key);
        int index = findNode(hash);
        if (index != -1) {
            Node node = nodes[index];
            if (node.delete(key) != null) {
                LOGGER.info("Deleted data with key: {} from node: ", key, node.getName());
                removeFromReplications(key, index);
                return true;
            } else {
                LOGGER.info("Couldn't delete data with key: {}", key);
                return false;
            }
        }
        return false;
    }

    private void removeFromReplications(String key, int fromNodeIndex) {
        for (int i = 0; i < config.getReplication(); i++) {
            int index = fromNodeIndex < nodes.length - 1 ? fromNodeIndex + 1 : 0;
            nodes[index].delete(key);
            LOGGER.info("Removed key: {} from replicated node: {}", key, nodes[index].getName());
            fromNodeIndex = index;
        }
    }

    /**
     * Find node position for the given hash in clock-wise direction
     */
    private int findNode(int hash) {
        if (nodes[hash] != null) {
            return hash;
        }
        int index = hash < nodes.length - 1 ? hash + 1 : hash;

        while (index != hash) {
            if (nodes[index] != null) {
                return index;
            } else {
                index = index < nodes.length - 1 ? index + 1 : 0;
            }
        }
        return -1;
    }

}
