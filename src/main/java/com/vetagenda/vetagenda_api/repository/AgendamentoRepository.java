package com.vetagenda.vetagenda_api.repository;

import com.vetagenda.vetagenda_api.domain.entity.AgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {
    List<AgendamentoEntity> findByVeterinarioId(Long veterinarioId);
    List<AgendamentoEntity> findByAnimalId(Long animalId);

    List<AgendamentoEntity> findByDataHoraBetween(LocalDateTime dataHoraAfter, LocalDateTime dataHoraBefore);

    List<AgendamentoEntity> existsByVeterinarioIdAndDataHoraAfter(Long veterinarioId, LocalDateTime dataHoraAfter);
}
