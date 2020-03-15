package dev.mateuszkowalczyk.treebuilder.service;

import dev.mateuszkowalczyk.treebuilder.model.Tree;
import org.springframework.stereotype.Service;

@Service
public class TreeService {
    public Tree getTree() {
        return new Tree();
    }
}
