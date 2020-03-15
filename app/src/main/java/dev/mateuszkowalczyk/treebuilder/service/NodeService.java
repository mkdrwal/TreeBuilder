package dev.mateuszkowalczyk.treebuilder.service;

import dev.mateuszkowalczyk.treebuilder.entity.Node;
import dev.mateuszkowalczyk.treebuilder.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NodeService {

    private NodeRepository nodeRepository;

    @Autowired
    public NodeService(
            NodeRepository nodeRepository
    ) {
        this.nodeRepository = nodeRepository;
    }

    public Node getParentNode() {
        Optional<Node> parentNode = this.nodeRepository.getByParentIsNull();

        return parentNode.orElseGet(this::createParentNode);
    }

    private Node createParentNode() {
        Node node = new Node();
        this.nodeRepository.saveAndFlush(node);

        return node;
    }

}
