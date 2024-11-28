import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroPonto {
    private int id;
    private Funcionario funcionario;
    private String data;    // Data formatada (AAAA-MM-DD)
    private String horario; // Horário formatado (HH:MM:SS)
    private char categoria; // 'e' para entrada, 's' para saída

    // Construtor
    public RegistroPonto(int id, Funcionario funcionario, char categoria) {
        this.id = id;
        this.funcionario = funcionario;

        // Captura a data e hora atual
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.data = agora.format(formatoData);
        this.horario = agora.format(formatoHora);

        // Valida a categoria
        if (categoria == 'e' || categoria == 's') {
            this.categoria = categoria;
        } else {
            this.categoria = 'e'; // Valor padrão
        }
    }

    public RegistroPonto(int id, Funcionario funcionario, String data, String horario, char categoria) {
      this.id = id;
      this.funcionario = funcionario;
      this.data = data;
      this.horario = horario;
      // Valida a categoria
      if (categoria == 'e' || categoria == 's') {
          this.categoria = categoria;
      } else {
          this.categoria = 'e'; // Valor padrão
      }
  }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
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
        if (categoria == 'e' || categoria == 's') {
            this.categoria = categoria;
        }
    }

    // Sobrescrevendo o método toString para exibição legível
    @Override
    public String toString() {
        return "ID: " + id +
                ", Funcionário: " + (funcionario != null ? funcionario.getNome() : "Nenhum") +
                ", Data: " + data +
                ", Horário: " + horario +
                ", Categoria: " + (categoria == 'e' ? "Entrada" : "Saída");
    }
}