package com.example.tp3;

import com.example.tp3.model.Aluno;
import com.example.tp3.model.Curso;
import com.example.tp3.repository.AlunoRepository;
import com.example.tp3.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void run(String... args) throws Exception {
        Curso curso1 = new Curso();
        curso1.setNome("Inglês");
        cursoRepository.save(curso1);

        Curso curso2 = new Curso();
        curso2.setNome("Espanhol");
        cursoRepository.save(curso2);

        Aluno aluno1 = new Aluno();
        aluno1.setNome("Duda");
        aluno1.getCursos().add(curso1);
        aluno1.getCursos().add(curso2);
        alunoRepository.save(aluno1);

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Rafaela");
        aluno2.getCursos().add(curso1);
        alunoRepository.save(aluno2);
    }
}