package com.vetagenda.vetagenda_api.repository;

import com.vetagenda.vetagenda_api.domain.entity.TutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<TutorEntity, Long> {
}
