package com.example.tp3.controller;

import com.example.tp3.model.Aluno;
import com.example.tp3.repository.AlunoRepository;
import com.example.tp3.model.Curso;
import com.example.tp3.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno getAlunoById(@PathVariable Long id) {
        return findAluno(id);
    }

    @PostMapping
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno updateAluno(@PathVariable Long id, @RequestBody Aluno aluno){
        Aluno alu = findAluno(id);

        alu.setNome(aluno.getNome());
        alu.setCursos(aluno.getCursos());
        return alunoRepository.save(alu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAluno(@PathVariable Long id) {
        Aluno aluno = findAluno(id);
        alunoRepository.delete(aluno);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/cursos")
    public List<Curso> getCursosByAlunoId(@PathVariable Long id) {
        Aluno aluno = findAluno(id);
        return aluno.getCursos();
    }

    @PostMapping("/{id}/cursos/{cursoId}")
    public Aluno addCursoToAluno(@PathVariable Long id, @PathVariable Long cursoId) {
        Aluno aluno = findAluno(id);
        Curso curso = findCurso(cursoId);
        aluno.getCursos().add(curso);
        return alunoRepository.save(aluno);
    }

    @DeleteMapping("/{id}/cursos/{cursoId}")
    public Aluno removeCursoFromAluno(@PathVariable Long id, @PathVariable Long cursoId) {
        Aluno aluno = findAluno(id);
        Curso curso = findCurso(cursoId);
        aluno.getCursos().remove(curso);
        return alunoRepository.save(aluno);
    }

    private Aluno findAluno(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado" + id));
    }

    private Curso findCurso(Long id) {
        return cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado: " + id));
    }
}
