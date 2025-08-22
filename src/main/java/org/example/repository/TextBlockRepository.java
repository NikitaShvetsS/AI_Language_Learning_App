package org.example.repository;

import org.example.model.entity.TextBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TextBlockRepository extends JpaRepository<TextBlock, UUID> {
}
