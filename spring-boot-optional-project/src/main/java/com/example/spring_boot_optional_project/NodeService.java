package com.example.spring_boot_optional_project;

import com.example.spring_boot_optional_project.NetworkNode;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NodeService {

    private final Map<Long, NetworkNode> nodeStore = new HashMap<>();
    private long nextId = 1;

    public NetworkNode addNode(NetworkNode node) {
        node.setId(nextId++);
        nodeStore.put(node.getId(), node);
        return node;
    }

    public Optional<NetworkNode> getNode(Long id) {
        return Optional.ofNullable(nodeStore.get(id));
    }

    public Optional<NetworkNode> updateNode(Long id, NetworkNode updatedNode) {
        if (nodeStore.containsKey(id)) {
            updatedNode.setId(id);
            nodeStore.put(id, updatedNode);
            return Optional.of(updatedNode);
        }
        return Optional.empty();
    }

    public boolean deleteNode(Long id) {
        return nodeStore.remove(id) != null;
    }


    public List<NetworkNode> getAllNodes() {
        return new ArrayList<>(nodeStore.values());
    }
}
