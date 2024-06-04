package com.example.tp3.controller;

import com.example.tp3.model.*;
import com.example.tp3.model.Curso;
import com.example.tp3.repository.AlunoRepository;
import com.example.tp3.repository.CursoRepository;
import com.example.tp3.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Curso> getAll() {
        return cursoService.getAllCursos();
    }

    @GetMapping("/{id}")
    public Curso getById(@PathVariable Long id) {
        return findCursoCache(id);
    }

    @PostMapping
    public Curso create(@RequestBody Curso curso) {
        return cursoService.saveCurso(curso);
    }

    @PutMapping("/{id}")
    public Curso update(@PathVariable Long id, @RequestBody Curso curso){
        Curso cur = findCurso(id);

        cur.setNome(curso.getNome());
        return cursoRepository.save(cur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Curso curso = findCursoCache(id);
        if(curso != null) {
            cursoService.deleteCurso(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/alunos")
    public List<Aluno> getAlunosByCursoId(@PathVariable Long id) {
        Curso curso = findCurso(id);
        return curso.getAlunos();
    }

    @PostMapping("/{id}/alunos/{alunoId}")
    public Curso addAlunoToCurso(@PathVariable Long id, @PathVariable Long alunoId) {
        Curso curso = findCurso(id);
        Aluno aluno = findAluno(alunoId);
        curso.getAlunos().add(aluno);
        return cursoRepository.save(curso);
    }

    @DeleteMapping("/{id}/alunos/{alunoId}")
    public Curso removeAlunoFromCurso(@PathVariable Long id, @PathVariable Long alunoId) {
        Curso curso = findCurso(id);
        Aluno aluno = findAluno(alunoId);
        curso.getAlunos().remove(aluno);
        return cursoRepository.save(curso);
    }

    private Curso findCurso(Long id) {
        return cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado" + id));
    }

    private Curso findCursoCache(Long id) {
        return cursoService.getCursoById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado" + id));
    }

    private Aluno findAluno(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado: " + id));
    }
}
