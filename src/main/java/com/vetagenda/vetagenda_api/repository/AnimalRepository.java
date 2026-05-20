package com.vetagenda.vetagenda_api.repository;

import com.vetagenda.vetagenda_api.domain.entity.AnimalEntity;
import com.vetagenda.vetagenda_api.domain.entity.TutorEntity;
import com.vetagenda.vetagenda_api.domain.entity.VeterinarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {
    List<AnimalEntity> findByTutorId(Long tutorId);
}
