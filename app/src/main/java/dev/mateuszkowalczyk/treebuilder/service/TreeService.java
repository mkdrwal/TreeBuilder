package dev.mateuszkowalczyk.treebuilder.service;

import dev.mateuszkowalczyk.treebuilder.builder.TreeBuilder;
import dev.mateuszkowalczyk.treebuilder.entity.Node;
import dev.mateuszkowalczyk.treebuilder.model.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreeService {
    private NodeService nodeService;

    @Autowired
    public TreeService(
            NodeService nodeService
    ) {
        this.nodeService = nodeService;
    }

    public Tree getTree() {
        Node parentNode = this.nodeService.getParentNode();

        TreeBuilder treeBuilder = new TreeBuilder(parentNode);
        return treeBuilder.buildTree();
    }
}
