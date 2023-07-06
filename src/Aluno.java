import java.io.BufferedReader;
import java.io.IOException;

public class Aluno extends Pessoa implements Comparable<Aluno> {

    private String matricula;

    public Aluno(BufferedReader b) throws IOException {
        super(b);
        this.matricula = b.readLine();
    }

    public String getMatricula() {
        return this.matricula;
    }

    @Override
    public int compareTo(Aluno o) {
        int comparator = this.nome.compareTo(o.getNome());
        if (comparator == 0) {
            return this.matricula.compareTo(o.getMatricula());
        }
        return comparator;
    }

}
