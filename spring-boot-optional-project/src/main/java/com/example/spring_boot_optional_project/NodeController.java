package com.example.spring_boot_optional_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nodes")
public class NodeController {

    @Autowired
    private NetworkNodeRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity<NetworkNode> addNode(@RequestBody NetworkNode node) {
        System.out.println("....I was here.....");
        NetworkNode savedNode = repository.save(node);
        System.out.println("....After save node.....");
        return ResponseEntity.ok(savedNode);
    }

    // Get all nodes
    @GetMapping
    public List<NetworkNode> getAllNodes() {
        return repository.findAll();
    }

    // Optional: get node by id
    @GetMapping("/{id}")
    public ResponseEntity<NetworkNode> getNodeById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateNode(@PathVariable Long id, @RequestBody NetworkNode updatedNode) {
        return repository.findById(id)
                .map(existingNode -> {
                    existingNode.setName(updatedNode.getName());
                    existingNode.setLocation(updatedNode.getLocation());
                    existingNode.setLatitude(updatedNode.getLatitude());
                    existingNode.setLongitude(updatedNode.getLongitude());
                    repository.save(existingNode);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNode(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
