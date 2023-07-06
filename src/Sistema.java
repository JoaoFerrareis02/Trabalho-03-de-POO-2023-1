import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sistema {

    private List<Aluno> alunos = new ArrayList<>();
    private List<Professor> professores = new ArrayList<>();
    private List<Turma> turmas = new ArrayList<>();

    public Sistema() {
        carregarAlunos();
        carregarProfessores();
        carregarTurmas();
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void carregarAlunos() {
        try (BufferedReader b = new BufferedReader(new FileReader("alunos.txt"))) {
            int x = Integer.parseInt(b.readLine());
            //Adiciona o aluno a lista de alunos
            for (int i = 0; i < x; i++) {
                Aluno aluno = new Aluno(b);
                this.alunos.add(aluno);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo 'alunos.txt' não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro: Problema na leitura de dados.");
        }
    }

    public void carregarProfessores() {
        try (BufferedReader b = new BufferedReader(new FileReader("professores.txt"))) {
            int x = Integer.parseInt(b.readLine());
            //Adiciona o professor a lista de professores
            for (int i = 0; i < x; i++) {
                Professor professor = new Professor(b);
                this.professores.add(professor);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo 'professores.txt' não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro: Problema na leitura de dados.");
        }
    }

    public void carregarTurmas() {
        try (BufferedReader b = new BufferedReader(new FileReader("turmas.txt"))) {
            int x = Integer.parseInt(b.readLine());
            for (int i = 0; i < x; i++) {
                String nomeDisciplina = b.readLine();
                int ano = Integer.parseInt(b.readLine());
                int semestre = Integer.parseInt(b.readLine());
                String cpfProfessor = b.readLine();
                //Realiza a localização do professor, caso não encontre aborta o programa.
                Professor professor = localizarProfessor(cpfProfessor);
                Turma turma = new Turma(nomeDisciplina, ano, semestre, professor);
                //Adiciona os alunos
                int y = Integer.parseInt(b.readLine());
                for (int j = 0; j < y; j++) {
                    boolean continua = true;
                    while (continua && j < y) {
                        try {
                            String matricula = b.readLine();
                            double nota = Double.parseDouble(b.readLine());
                            //Realiza a localização do programa. Caso não encrontre, pula pro proximo.
                            Aluno aluno = localizarAluno(matricula);
                            AlunoNota alunoNota = new AlunoNota(aluno, nota);
                            turma.getAlunos().add(alunoNota);
                            continua = false;
                        } catch (AlunoNotFoundException e) {
                            System.out.println(e.getMessage());
                            j++; //Incrementa o contador j.
                        }
                    }
                }
                //Adiciona a turma a lista de turmas
                this.turmas.add(turma);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo 'turmas.txt' não encontrado.");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Conversão de String para número inválido. ");
        } catch (IOException e) {
            System.out.println("Erro: Problema na leitura de dados.");
        } catch (ProfessorNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private Professor localizarProfessor(String cpf) throws ProfessorNotFoundException {
        Professor prof = null;
        for (Professor p : this.getProfessores()) {
            if (p.getCpf().equals(cpf)) {
                prof = p;
                break;
            }
        }
        if (prof == null) {
            throw new ProfessorNotFoundException("Aviso: professor não encontrado.");
        }
        return prof;
    }

    private Aluno localizarAluno(String matricula) throws AlunoNotFoundException {
        Aluno aluno = null;
        for (Aluno a : this.getAlunos()) {
            if (a.getMatricula().equals(matricula)) {
                aluno = a;
                break;
            }
        }
        if (aluno == null) {
            throw new AlunoNotFoundException("Aviso: Aluno não encontrado");
        }
        return aluno;
    }

    public void exibirNotas() {
        Collections.sort(this.getTurmas());
        try (BufferedWriter b = new BufferedWriter(new FileWriter("saida.txt"))) {
            for (Turma t : this.getTurmas()) {
                t.exibirNotas(b);
            }
        } catch (IOException e) {
            System.out.println("Erro: Problema na saída de dados.");
        }
    }

}
