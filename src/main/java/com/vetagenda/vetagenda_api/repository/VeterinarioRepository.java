package com.vetagenda.vetagenda_api.repository;

import com.vetagenda.vetagenda_api.domain.entity.VeterinarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepository extends JpaRepository<VeterinarioEntity, Long> {
}
