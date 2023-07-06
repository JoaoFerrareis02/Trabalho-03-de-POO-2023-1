public class AlunoNota implements Comparable<AlunoNota> {

    private Aluno aluno;
    private double nota;

    public AlunoNota(Aluno aluno, double nota) {
        this.aluno = aluno;
        this.nota = nota;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public int compareTo(AlunoNota o) {
        int comparator = -Double.compare(this.nota, o.getNota());
        if (comparator == 0) {
            return this.aluno.compareTo(o.getAluno());
        }
        return comparator;
    }

}
