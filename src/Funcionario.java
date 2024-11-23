
public class Funcionario{
  private int id;
  private String nome;
  private String cpf;
  private Cargo cargo;

  public Funcionario(int id, String nome, String cpf) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
}

  public Funcionario(int id, String nome, String cpf, Cargo cargo) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.cargo = cargo;
}

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public String getCpf() {
    return cpf;
}

public void setCpf(String cpf) {
    this.cpf = cpf;
}

public Cargo getCargo() {
    return cargo;
}

public void setCargo(Cargo cargo) {
    this.cargo = cargo;
} 

public String chaves(){
    return "Id;Nome;Cpf;Cargo\n";
}


public String toString(boolean exibicao) {
    if(exibicao){
        return "Id: " + id + " - Nome " + nome + " - CPF: " + cpf + "- Cargo: " + (cargo != null ? cargo.getNome() + "\n" : "sem cargo \n");
    }
    return id + ";" + nome + ";" + cpf + ";" + (cargo != null ? cargo.getNome() + "\n" : "sem cargo \n");
}
} 