package com.vetagenda.vetagenda_api.controller;


import com.vetagenda.vetagenda_api.domain.entity.TutorEntity;
import com.vetagenda.vetagenda_api.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tutores")
public class TutorController {

    private final TutorRepository tutorRepository;
        // AINDA SERÁ FEITO

}
