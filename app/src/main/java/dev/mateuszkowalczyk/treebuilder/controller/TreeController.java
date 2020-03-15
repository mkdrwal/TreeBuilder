package dev.mateuszkowalczyk.treebuilder.controller;

import dev.mateuszkowalczyk.treebuilder.model.Tree;
import dev.mateuszkowalczyk.treebuilder.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/tree")
public class TreeController {
    private TreeService treeService;

    @Autowired
    public TreeController(
            TreeService treeService
    ) {
        this.treeService = treeService;
    }

    @GetMapping(value = {"", "/"})
    @ResponseBody
    public Tree getTree() {
        return this.treeService.getTree();
    }

}
