package dev.mateuszkowalczyk.treebuilder.service;

import dev.mateuszkowalczyk.treebuilder.entity.Node;
import dev.mateuszkowalczyk.treebuilder.exception.CannotCreateNodeException;
import dev.mateuszkowalczyk.treebuilder.model.TreeNodeSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreeNodeService {
    private NodeService nodeService;

    @Autowired
    public TreeNodeService(
            NodeService nodeService
    ) {
        this.nodeService = nodeService;
    }

    public Long create(TreeNodeSchema treeNode) throws CannotCreateNodeException {
        Node node = this.nodeService.create(treeNode);

        return node.getId();
    }
}
