package dev.mateuszkowalczyk.treebuilder.repository;

import dev.mateuszkowalczyk.treebuilder.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
    Optional<Node> getByParentIsNull();
}
