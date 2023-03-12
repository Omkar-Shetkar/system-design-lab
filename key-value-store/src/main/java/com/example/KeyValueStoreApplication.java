package com.example;

import com.example.server.HashRing;
import com.example.server.Node;
import com.example.server.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@SpringBootApplication
public class KeyValueStoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KeyValueStoreApplication.class, args);
	}

	private @Autowired ServerConfig serverConfig;

	private @Autowired HashRing hashRing;

	private static final Logger LOGGER = LoggerFactory.getLogger(KeyValueStoreApplication.class);

	@Override
	public void run(String... args) throws Exception {
		init();
		LOGGER.info(hashRing.toString());
	}

	private void init() {
		int nodes = serverConfig.getNodes();
		for(int i=0; i<nodes; i++) {
			Node node = new Node("node-"+i,new HashMap<>());
			hashRing.addNode(node);
		}


	}
}
