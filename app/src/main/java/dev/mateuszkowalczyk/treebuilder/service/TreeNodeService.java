package dev.mateuszkowalczyk.treebuilder.service;

import dev.mateuszkowalczyk.treebuilder.entity.Node;
import dev.mateuszkowalczyk.treebuilder.exception.NodeException;
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

    public Long create(TreeNodeSchema treeNode) throws NodeException {
        Node node = this.nodeService.create(treeNode);

        return node.getId();
    }

    public void update(Long id, TreeNodeSchema nodeSchema) throws NodeException {
        this.nodeService.update(id, nodeSchema);
    }

    public void delete(Long id) {
        this.nodeService.delete(id);
    }
}
