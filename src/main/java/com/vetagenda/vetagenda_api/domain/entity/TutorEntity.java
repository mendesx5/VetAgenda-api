package com.vetagenda.vetagenda_api.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb-tutor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    private String cpf;

    private String telefone;

    @Email
    private String email;

    @OneToMany(mappedBy = "tutor")
    private List<AnimalEntity> animals;

}