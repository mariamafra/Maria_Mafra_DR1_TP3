package com.example.tp3.service;

import com.example.tp3.model.Curso;
import com.example.tp3.repository.CursoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Cacheable(value="cursos")
    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Cacheable(value="cursos")
    public List<Curso> getAllCursos(){
        return cursoRepository.findAll();
    }

    @Cacheable(value="cursos", key="#id")
    public Optional<Curso> getCursoById(Long id) {
        return cursoRepository.findById(id);
    }

    @CacheEvict(value="cursos", key="#id")
    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    @CacheEvict(value="cursos", key="#id")
    public Curso updateCurso(Long id, Curso cursoUpdate) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setNome(cursoUpdate.getNome());
            curso.setAlunos(cursoUpdate.getAlunos());
            return cursoRepository.save(curso);
        }).orElseGet(() -> {
            cursoUpdate.setId(id);
            return cursoRepository.save(cursoUpdate);
        });
    }
}
