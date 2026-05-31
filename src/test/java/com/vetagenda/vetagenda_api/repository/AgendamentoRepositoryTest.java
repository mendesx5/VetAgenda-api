package com.vetagenda.vetagenda_api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class AgendamentoRepositoryTest {

    @Test
    void findByVeterinarioId() {
    }

    @Test
    void findByAnimalId() {
    }

    @Test
    void findByDataHoraBetween() {
    }

    @Test
    void existsByVeterinarioIdAndDataHora() {
    }
}