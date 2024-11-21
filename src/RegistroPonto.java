
public class RegistroPonto {
  private int id;
  private Funcionario funcionario;
  private String data;
  private String horario;
  private char categoria;

  public RegistroPonto(int id, Funcionario funcionario, String data, String horario, char categoria){
    this.id = id;
    this.funcionario = funcionario;
    this.data = data;
    this.horario = horario;
    if (categoria == 'e' || categoria == 's') {
      this.categoria = categoria;
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
      this.id = id;
  }

  public Funcionario getfuncionario() {
    return funcionario;
  }

  public void setfuncionario(Funcionario funcionario) {
      this.funcionario = funcionario;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
      this.data = data;
  }

  public String getHorario() {
    return horario;
  }

  public void setHorario(String horario) {
      this.horario = horario;
  }

  public char getCategoria() {
    return categoria;
  }

  public void setCategoria(char categoria) {
      this.categoria = categoria;
  }

  // @Override
  // public String toString() {
  //   return "ID: " + id + ", Nome: " + nome + ", CPF: " + cpf + ", Cargo: " + (cargo != null ? cargo.getNome() : "Nenhum");
  // }


}