import java.io.BufferedReader;
import java.io.IOException;

public abstract class Pessoa {

    protected String nome;
    protected String cpf;

    public Pessoa(BufferedReader b) throws IOException {
        this.nome = b.readLine();
        this.cpf = b.readLine();
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }

}
