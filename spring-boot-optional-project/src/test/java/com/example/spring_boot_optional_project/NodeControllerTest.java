package com.example.spring_boot_optional_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NodeControllerTest {

    @Autowired
    private NodeService nodeService;

    private NetworkNode testNode;

    @BeforeEach
            void SetUp() {
        testNode = new NetworkNode("Test Node", "Athlone", 1234, 5678);
        nodeService.addNode(testNode);
    }

    @Test
    public void  NodeExistTest(){
        Optional<NetworkNode> foundNode = nodeService.getNode(testNode.getId());
       assertTrue(foundNode.isPresent());
    }


    @Test
    void testDeleteNode(){
        long idToBeDeleted = testNode.getId();

        boolean deleted = nodeService.deleteNode(idToBeDeleted);
        assertTrue(deleted);

        Optional<NetworkNode> afterDeletion = nodeService.getNode(idToBeDeleted);
        assertTrue(afterDeletion.isEmpty());
    }

    @Test
    void testAddNode(){
        NetworkNode testNewNode;
        testNewNode = new NetworkNode("Test new Node", "Athenry", 1254, 58);
        nodeService.addNode(testNewNode);
        long afterAdded = testNewNode.getId();
        Optional<NetworkNode> afterInserted = nodeService.getNode(afterAdded);
        assertTrue(afterInserted.isPresent());

    }

    @Test
    void testGetAllNodes(){
//        List<NetworkNode> allNodes = nodeService.getAllNodes();
        int originalSize = nodeService.getAllNodes().size();

        nodeService.addNode(new NetworkNode("Test Node 1", "Athlone", 1234, 5678));
        nodeService.addNode(new NetworkNode("Test Node 2", "Athlone", 1234, 5678));
        nodeService.addNode(new NetworkNode("Test Node 3", "Athlone", 1234, 5678));

        int newSize = nodeService.getAllNodes().size();
        assertEquals(newSize, originalSize + 3);
    }

}