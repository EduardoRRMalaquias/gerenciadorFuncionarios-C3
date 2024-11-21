public class Funcionario{
  private int id;
  private String nome;
  private String cpf;
  private Cargo cargo;

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

@Override
public String toString() {
    return "ID: " + id + ", Nome: " + nome + ", CPF: " + cpf + ", Cargo: " + (cargo != null ? cargo.getNome() : "Nenhum");
}
} 