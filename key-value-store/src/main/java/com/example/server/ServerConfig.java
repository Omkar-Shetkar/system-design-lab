package com.example.server;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "server")
public class ServerConfig {

    private int nodes;

    private int hashSpace;

    private int replication;

    public void setHashSpace(int hashSpace) {
        this.hashSpace = hashSpace;
    }

    public int getHashSpace() {
        return hashSpace;
    }

    public void setNodes(int nodes) {
        this.nodes = nodes;
    }

    public int getNodes() {
        return nodes;
    }

    public int getReplication() {
        return replication;
    }

    public void setReplication(int replication) {
        this.replication = replication;
    }
}
