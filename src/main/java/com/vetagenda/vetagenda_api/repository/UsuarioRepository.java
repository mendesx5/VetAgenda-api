package com.vetagenda.vetagenda_api.repository;

import com.vetagenda.vetagenda_api.domain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UserDetails findByLogin(String login);
}
