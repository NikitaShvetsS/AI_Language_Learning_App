package org.example.repository;

import org.example.model.entity.User;
import org.example.model.entity.DailyPack;
import org.example.model.entity.VocabBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VocabBlockRepository extends JpaRepository<VocabBlock, UUID> {
    List<VocabBlock> findByUser(User user);
    List<VocabBlock> findBySourcePack(DailyPack pack);
    boolean existsByUserAndWordIgnoreCase(User user, String word);
}
