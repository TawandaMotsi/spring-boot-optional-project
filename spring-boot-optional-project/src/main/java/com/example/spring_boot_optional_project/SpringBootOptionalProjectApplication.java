package com.example.spring_boot_optional_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class SpringBootOptionalProjectApplication {

    @Autowired
    private static NetworkNodeRepository repository;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBootOptionalProjectApplication.class, args);

        NetworkNodeRepository repository = context.getBean(NetworkNodeRepository.class);

        NetworkNode node = new NetworkNode("Test Node", "Athlone", 1234, 5678);
        repository.save(node);
        NetworkNode node1 = new NetworkNode("Test Node", "Athlone", 1234, 5678);
        repository.save(node1);
        NetworkNode node2 = new NetworkNode("Test Node", "Athlone", 1234, 5678);
        repository.save(node2);
        NetworkNode node3 = new NetworkNode("Test Node", "Athlone", 1234, 5678);
        repository.save(node3);

		//
		repository.deleteById(3L);



		NetworkNode node5 = new NetworkNode("Test Node", "Athlone", 1234, 5678);


        System.out.println("Node saved: " + node.getId());

        Optional<NetworkNode> optionalNode = repository.findById(node.getId());
        if (optionalNode.isPresent()) {
            NetworkNode nodeToUpdate = optionalNode.get();
            nodeToUpdate.setName("Updated Node Name");
            nodeToUpdate.setLocation("Dublin");
            nodeToUpdate.setLatitude(4321);
            nodeToUpdate.setLongitude(8765);

            repository.save(nodeToUpdate);
            System.out.println("Updated Node: " + nodeToUpdate.getName() + ", Location: " + nodeToUpdate.getLocation());
        } else {
            System.out.println("Node not found for update.");
        }

    }
}


