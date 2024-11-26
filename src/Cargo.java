public class Cargo {
  private int id;
  private String nome;
  private String descricao;
  private double salario;

  public Cargo(int id, String nome, String descricao, double salario) {
      this.id = id;
      this.nome = nome;
      this.descricao = descricao;
      this.salario = salario;
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

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public double getSalario() {
      return salario;
  }

  public void setSalario(double salario) {
      this.salario = salario;
  }

  @Override
  public String toString() {
      return "ID: " + id + ", Nome: " + nome + ", Descricao: " + descricao + ", Salário: R$" + salario;
  }
}