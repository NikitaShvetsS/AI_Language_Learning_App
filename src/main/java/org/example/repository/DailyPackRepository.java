package org.example.repository;

import org.example.model.entity.DailyPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DailyPackRepository extends JpaRepository<DailyPack, UUID> {
}
