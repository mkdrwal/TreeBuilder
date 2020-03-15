package dev.mateuszkowalczyk.treebuilder.controller;

import dev.mateuszkowalczyk.treebuilder.exception.NodeException;
import dev.mateuszkowalczyk.treebuilder.model.TreeNodeSchema;
import dev.mateuszkowalczyk.treebuilder.service.TreeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/node")
public class TreeNodeController {

    private TreeNodeService treeNodeService;

    @Autowired
    public TreeNodeController(
            TreeNodeService treeNodeService
    ) {

        this.treeNodeService = treeNodeService;
    }

    @PostMapping(value = {"", "/"})
    public Long createNode(@RequestBody TreeNodeSchema nodeSchema) throws NodeException {
        return this.treeNodeService.create(nodeSchema);
    }

    @PutMapping(value = "/{nodeId}")
    public void updateNode(@RequestBody TreeNodeSchema nodeSchema, @PathVariable(value = "nodeId") Long id) throws NodeException {
        this.treeNodeService.update(id, nodeSchema);
    }
}
