package com.oocl.web.sampleWebApp.jpaSample.repository;

import com.oocl.web.sampleWebApp.jpaSample.entity.SingleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingleEntityRepository extends JpaRepository<SingleEntity, Long> {
    void findByName(String name);
}
