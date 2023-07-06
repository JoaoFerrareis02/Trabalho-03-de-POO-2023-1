import java.io.BufferedReader;
import java.io.IOException;

public class Professor extends Pessoa implements Comparable<Professor> {

    public Professor(BufferedReader b) throws IOException {
        super(b);
    }

    @Override
    public int compareTo(Professor o) {
        int comparator = this.nome.compareTo(o.getNome());
        if (comparator == 0) {
            return this.cpf.compareTo(o.getCpf());
        }
        return comparator;
    }

}
