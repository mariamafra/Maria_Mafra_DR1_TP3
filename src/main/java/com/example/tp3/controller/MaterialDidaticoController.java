package com.example.tp3.controller;

import com.example.tp3.model.Curso;
import com.example.tp3.model.MaterialDidatico;
import com.example.tp3.service.MaterialDidaticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materiais")
public class MaterialDidaticoController {
    @Autowired
    private MaterialDidaticoService materialDidaticoService;

    @PostMapping
    public MaterialDidatico salvar(@RequestBody MaterialDidatico materialDidatico) {
        return materialDidaticoService.saveMat(materialDidatico);
    }

    @GetMapping
    public List<MaterialDidatico> listAll(){
        return materialDidaticoService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<MaterialDidatico> getById(@PathVariable String id) {
        return materialDidaticoService.getMatById(id);
    }


}
