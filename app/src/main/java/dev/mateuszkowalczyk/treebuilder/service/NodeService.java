package dev.mateuszkowalczyk.treebuilder.service;

import dev.mateuszkowalczyk.treebuilder.entity.Node;
import dev.mateuszkowalczyk.treebuilder.exception.NodeException;
import dev.mateuszkowalczyk.treebuilder.model.TreeNodeSchema;
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

    public Node create(TreeNodeSchema treeNode) throws NodeException {
        Node node = new Node();
        Optional<Node> parent;
        if (treeNode.getParentId() == null) {
            throw new NodeException("Parent id cannot be null");
        } else {
            parent = this.nodeRepository.findById(treeNode.getParentId());

            if (!parent.isPresent()) {
                throw new NodeException("Parent on this id not exists");
            }
        }

        node.setParent(parent.get());

        this.nodeRepository.saveAndFlush(node);
        return node;
    }

    public void update(Long id, TreeNodeSchema nodeSchema) throws NodeException {
        Optional<Node> optionalNode = this.nodeRepository.findById(id);
        if (optionalNode.isPresent()) {
            Node node = optionalNode.get();
            updateParent(node, nodeSchema);
            updateValue(node, nodeSchema);

            this.nodeRepository.saveAndFlush(node);
        } else {
            throw new NodeException(String.format("Cannot find node with id: %d", id));
        }
    }

    private void updateValue(Node node, TreeNodeSchema nodeSchema) {
        node.setValue(nodeSchema.getValue());
    }

    private void updateParent(Node node, TreeNodeSchema nodeSchema) throws NodeException {
        if (nodeSchema.getParentId() != null) {
            Optional<Node> parentNode = this.nodeRepository.findById(nodeSchema.getParentId());

            if (parentNode.isPresent()) {
                node.setParent(parentNode.get());
            } else {
                throw new NodeException(String.format("Cannot find parent with id: %d", nodeSchema.getParentId()));
            }
        }
    }
}
