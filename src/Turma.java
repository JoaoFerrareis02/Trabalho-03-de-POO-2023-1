import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Turma implements Comparable<Turma> {

    private String nomeDisciplina;
    private int ano;
    private int semestre;
    private Professor professor;
    private List<AlunoNota> alunos = new ArrayList<>();

    public Turma(String nomeDisciplina, int ano, int semestre, Professor professor) {
        this.nomeDisciplina = nomeDisciplina;
        this.ano = ano;
        this.semestre = semestre;
        this.professor = professor;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public int getAno() {
        return ano;
    }

    public int getSemestre() {
        return semestre;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<AlunoNota> getAlunos() {
        return alunos;
    }

    @Override
    public int compareTo(Turma o) {
        //Comparar anos
        if (this.ano < o.getAno()) return 1;
        if (this.ano > o.getAno()) return -1;
        //Comparar semestres
        if (this.semestre < o.getSemestre()) return 1;
        if (this.semestre > o.getSemestre()) return -1;
        //Comparar disciplinas, caso igual comparar professores
        int comparator = this.nomeDisciplina.compareTo(o.getNomeDisciplina());
        if (comparator == 0) {
            return this.professor.compareTo(o.getProfessor());
        }
        return comparator;
    }

    public void exibirNotas(BufferedWriter b) throws IOException{
        Collections.sort(this.getAlunos());
        b.write(this.getNomeDisciplina() + " (" + this.getAno() + "/" + this.getSemestre() + ") - " + this.getProfessor().getNome() + "\n");
        for (AlunoNota a : this.getAlunos()) {
            b.write("- " + a.getAluno().getNome() + " (" + a.getAluno().getMatricula() + "): " + a.getNota() + "\n");
        }
    }

}
