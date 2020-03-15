package dev.mateuszkowalczyk.treebuilder.controller;

import dev.mateuszkowalczyk.treebuilder.exception.CannotCreateNodeException;
import dev.mateuszkowalczyk.treebuilder.model.TreeNodeSchema;
import dev.mateuszkowalczyk.treebuilder.service.TreeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Long createNode(@RequestBody TreeNodeSchema treeNode) throws CannotCreateNodeException {
        return this.treeNodeService.create(treeNode);
    }
}
