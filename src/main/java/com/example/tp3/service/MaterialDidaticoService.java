package com.example.tp3.service;

import com.example.tp3.model.Curso;
import com.example.tp3.model.MaterialDidatico;
import com.example.tp3.repository.MaterialDidaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialDidaticoService {
    @Autowired
    private MaterialDidaticoRepository materialDidaticoRepository;

    public List<MaterialDidatico> getAll() {
        return materialDidaticoRepository.findAll();
    }

    public Optional<MaterialDidatico> getMatById(String id) {
        return materialDidaticoRepository.findById(id);
    }

    public MaterialDidatico saveMat(MaterialDidatico material) {
        return materialDidaticoRepository.save(material);
    }

    public void deleteMat(String id) {
        materialDidaticoRepository.deleteById(id);
    }

    public MaterialDidatico updateMat(String id, MaterialDidatico matUpdate) {
        return materialDidaticoRepository.findById(id).map(mat -> {
            mat.setTitulo(matUpdate.getTitulo());
            return materialDidaticoRepository.save(mat);
        }).orElseGet(() -> {
            matUpdate.setId(id);
            return materialDidaticoRepository.save(matUpdate);
        });
    }
}
