import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static String arquivoFuncionarios = "src/dados/Funcionarios.csv";
    private static String arquivoCargos = "src/dados/Cargos.csv";
    private static String arquivoPontos = "src/dados/RegistrosPontos.csv";
    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static List<Cargo> cargos = new ArrayList<>();
    private static List<RegistroPonto> registrosPontos = new ArrayList<>();
    private static int funcionarioId = 1;
    private static int cargoId = 1;
    private static int registroPontosId = 1;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        carregarCargosDoArquivo();
        carregarFuncionariosDoArquivo();
        carregarRegistroPontoDoArquivo();

        while (true) {
            System.out.println("\n=#==========#=");
            System.out.println("=#== Menu ==#=");
            System.out.println("=#==========#=\n");
            System.out.println("1. Gerenciar Funcionarios");
            System.out.println("2. Gerenciar Cargos");
            System.out.println("3. Gerenciar Registros de Ponto");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opcao: ");
            int opcao = scanner.nextInt(); 

            switch (opcao) {
                case 1:
                    gerenciarFuncionarios();
                    break;
                case 2:
                    gerenciarCargos();
                    break;
                case 3:
                    gerenciarRegistrosPontos();
                    break;
                case 4:
                    System.out.println("Encerrando...");
                    salvarCargosNoArquivo();
                    salvarFuncionariosNoArquivo();
                    salvarRegistrosNoArquivo();
                    scanner.close();
                    return;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private static void gerenciarFuncionarios() {
        System.out.println("\n=#===============================#=");
        System.out.println("=#=== Gerenciar Funcionarios: ===#=");
        System.out.println("=#===============================#=\n");
        System.out.println("1. Cadastrar");
        System.out.println("2. Editar");
        System.out.println("3. Consultar");
        System.out.println("4. Apagar");
        System.out.print("Escolha uma opcao: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                cadastrarFuncionario();
                break;
            case 2:
                editarFuncionario();
                break;
            case 3:
                consultarFuncionarios();
                break;
            case 4:
                apagarFuncionario();
                break;
            default:
                System.out.println("Opcao invalida!");
        }
    }

    private static void gerenciarCargos() {
        System.out.println("\n=#=========================#=");
        System.out.println("=#=== Gerenciar Cargos: ===#=");
        System.out.println("=#=========================#=\n");
        System.out.println("1. Cadastrar");
        System.out.println("2. Editar");
        System.out.println("3. Consultar");
        System.out.println("4. Consultar Cargos com IDs");
        System.out.println("5. Apagar");
        System.out.print("Escolha uma opcao: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                cadastrarCargo();
                break;
            case 2:
                editarCargo();
                break;
            case 3:
                consultarCargos();
                break;
            case 4:
                consultarCargosComIds();
                break;
            case 5:
                apagarCargo();
                break;
            default:
                System.out.println("Opcao invalida!");
        }
    }

    private static void gerenciarRegistrosPontos() {
      System.out.println("\n=#=====================================#=");
      System.out.println("=#=== Gerenciar Registros de Pontos ===#=");
      System.out.println("=#=====================================#=\n");
      System.out.println("1. Registrar Ponto");
      System.out.println("2. Editar Registro de Ponto");
      System.out.println("3. Consultar Registros de Pontos");
      System.out.println("4. Apagar Registro de Ponto");
      System.out.print("Escolha uma opcao: ");

      int opcao = scanner.nextInt();

      switch (opcao) {
          case 1:
              registrarPonto();
              break;
          case 2:
              editarRegistroPonto();
              break;
          case 3:
              consultarRegistrosPontos();
              break;
          case 4:
              apagarRegistroPonto();
              break;
          default:
              System.out.println("Opcao invalida! Tente novamente.");
      }
  }

    // Métodos para Funcionários
    private static void cadastrarFuncionario() {
        scanner.nextLine(); // Limpa o buffer após o último nextInt()
        System.out.print("Nome do funcionario: ");
        String nome = scanner.nextLine(); // Captura o nome completo
        System.out.print("CPF do funcionario: ");
        String cpf = scanner.next();
    
        if (cpfExiste(cpf)) {
            System.out.println("Erro: CPF ja cadastrado!");
            return;
        }
    
        consultarCargosComIds();
        System.out.print("ID do cargo: ");
        int idCargo = scanner.nextInt();
        Cargo cargo = buscarCargoPorId(idCargo);
        if (cargo != null) {
            funcionarios.add(new Funcionario(funcionarioId++, nome, cpf, cargo));
            System.out.println("Funcionario cadastrado!");
        } else {
            System.out.println("Cargo nao encontrado!");
        }
    }
    

    private static void editarFuncionario() {
        consultarFuncionarios();
        System.out.print("Deseja editar qual funcionario?");
        System.out.print("\nID do funcionario: ");
        int id = scanner.nextInt();
        Funcionario func = buscarFuncionarioPorId(id);
        if (func != null) {
            System.out.print("Novo nome: ");
            func.setNome(scanner.next());
            System.out.print("Novo CPF: ");
            String novoCpf = scanner.next();

            if (!func.getCpf().equals(novoCpf) && cpfExiste(novoCpf)) {
                System.out.println("Erro: CPF ja cadastrado!");
                return;
            }

            func.setCpf(novoCpf);
            consultarCargosComIds();
            System.out.print("Novo ID do cargo: ");
            int novoCargoId = scanner.nextInt();
            Cargo novoCargo = buscarCargoPorId(novoCargoId);
            if (novoCargo != null) {
                func.setCargo(novoCargo);
                System.out.println("Funcionario atualizado!");
            } else {
                System.out.println("Cargo nao encontrado!");
            }
        } else {
            System.out.println("Funcionario nao encontrado!");
        }
    }

    private static void consultarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }
        System.out.println("\nLista de Funcionarios:");
        funcionarios.forEach(f -> System.out.println(f.getId() + " - " + f.getNome() + " - " + f.getCpf() + " - " + f.getCargo().getNome()));
    }

    private static void apagarFuncionario() {
        consultarFuncionarios();
        System.out.print("Deseja apagar qual funcionario?");
        System.out.print("\nID do funcionario: ");
        int idApagar = scanner.nextInt();
        funcionarios.removeIf(f -> f.getId() == idApagar);
        System.out.println("Funcionario removido!");
    }

    // Métodos para Cargos
    private static void cadastrarCargo() {
        scanner.nextLine();
        System.out.print("Nome do cargo: ");
        String nome = scanner.nextLine();
        System.out.print("Descricao do cargo: ");
        String descricao = scanner.nextLine();
        System.out.print("Salario do cargo: ");
        double salario = scanner.nextDouble();
        cargos.add(new Cargo(cargoId++, nome, descricao, salario));
        System.out.println("Cargo cadastrado!");
    }

    private static void editarCargo() {
        consultarCargosComIds();
        System.out.print("Deseja editar qual Cargo?");
        System.out.print("\nID do cargo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha restante
    
        Cargo cargo = buscarCargoPorId(id);
        if (cargo != null) {
            System.out.print("Novo nome: ");
            cargo.setNome(scanner.nextLine()); // Permitir entrada com espaços
    
            System.out.print("Nova descricao: ");
            cargo.setDescricao(scanner.nextLine()); // Permitir entrada com espaços
    
            System.out.print("Novo salario: ");
            if (scanner.hasNextDouble()) {
                cargo.setSalario(scanner.nextDouble());
                scanner.nextLine(); // Consumir a quebra de linha restante
            } else {
                System.out.println("Entrada invalida para salario.");
                scanner.nextLine(); // Consumir entrada inválida
                return;
            }
    
            System.out.println("Cargo atualizado!");
        } else {
            System.out.println("Cargo nao encontrado!");
        }
    }    


    private static void consultarCargos() {
        if (cargos.isEmpty()) {
            System.out.println("Nenhum cargo cadastrado.");
            return;
        }
        System.out.println("\nLista de Cargos:");
        cargos.forEach(c -> System.out.println(c.getNome()));
    }

    private static void consultarCargosComIds() {
        if (cargos.isEmpty()) {
            System.out.println("Nenhum cargo cadastrado.");
            return;
        }
        System.out.println("\nLista de Cargos (ID - Nome - Salario):");
        cargos.forEach(c -> System.out.println(c.getId() + " - " + c.getNome() + " - R$ " + c.getSalario()));
    }

    private static void apagarCargo() {
        consultarCargosComIds();
        System.out.print("Deseja apagar qual Cargo?");
        System.out.print("\nID do cargo: ");
        int idApagar = scanner.nextInt();
        cargos.removeIf(c -> c.getId() == idApagar);
        System.out.println("Cargo removido!");
    }


    //Metodos para Registro de Ponto
    private static void registrarPonto() {
      scanner.nextLine(); // Limpa o buffer após o último nextInt()
      LocalDateTime agora = LocalDateTime.now();
      DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
      System.out.println("Ponto sera registrado em: " + agora.format(formatoData) + "as " + agora.format(formatoHora));
      consultarFuncionarios();
      System.out.print("ID do funcionario: ");
      int idFuncionario = scanner.nextInt();
      Funcionario funcionario = buscarFuncionarioPorId(idFuncionario);
  
      System.out.println("Categorias: ");
      System.out.println("[S] - Saida \n[E] - Entrada");
      char categoria = scanner.next().toLowerCase().charAt(0);
      if (funcionario != null) {
          registrosPontos.add(new RegistroPonto(registroPontosId++, funcionario, categoria));
          System.out.println("Resgistro cadastrado!");
      } else {
          System.out.println("Funcionario nao encontrado!");
      }
    }

    private static void editarRegistroPonto() {
      consultarRegistrosPontos();
      System.out.print("Deseja editar qual registro? ");
      System.out.print("\nID do resgistro: ");
      int id = scanner.nextInt();
      RegistroPonto ponto = buscarRegistroPontoPorId(id);
      if (ponto != null) {
        consultarFuncionarios();
        System.out.print("ID do novo funcionario: ");
        int idFuncionario = scanner.nextInt(); // Captura o nome completo
        Funcionario funcionario = buscarFuncionarioPorId(idFuncionario);
        ponto.setFuncionario(funcionario);

        System.out.println("Categorias: ");
        System.out.println("[S] - Saida \n [E] - Entrada");
        char categoria = scanner.next().toLowerCase().charAt(0);
          if (funcionario != null) {
            ponto.setCategoria(categoria);
            System.out.println("Registro atualizado!");
          } else {
            System.out.println("Cargo nao encontrado!");
          }
      } else {
          System.out.println("Registro nao encontrado!");
      }
  }

  private static void consultarRegistrosPontos() {
    if (registrosPontos.isEmpty()) {
        System.out.println("Nenhum registro cadastrado.");
        return;
    }
    System.out.println("\nLista de Registros:");
    registrosPontos.forEach(r -> System.out.println(r.getId() + " - " + r.getFuncionario().getNome() + " - " + r.getData() + " - " + r.getHorario() + " - " + (r.getCategoria() == 'e' ? "Entrada" : "Saída")));
}

  private static void apagarRegistroPonto() {
    consultarRegistrosPontos();
    System.out.print("Deseja apagar qual registro? ");
    System.out.print("\nID do registro: ");
    int idApagar = scanner.nextInt();
    registrosPontos.removeIf(r -> r.getId() == idApagar);
    System.out.println("Registro de ponto removido!");
  }


    // Métodos auxiliares
    private static Funcionario buscarFuncionarioPorId(int id) {
        return funcionarios.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }

    private static Cargo buscarCargoPorId(int id) {
        return cargos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    private static RegistroPonto buscarRegistroPontoPorId(int id) {
      return registrosPontos.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
  }

    private static boolean cpfExiste(String cpf) {
        return funcionarios.stream().anyMatch(f -> f.getCpf().equals(cpf));
    }

    private static void carregarFuncionariosDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoFuncionarios))) {
            String linha = reader.readLine(); // Pula o cabeçalho
            
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                
                String nome = dados[1];
              
                String cpf = dados[2];
               
                int idCargo = Integer.parseInt(dados[3].trim());
                
                Cargo cargo = buscarCargoPorId(idCargo);
                
                if (cargo != null) {
                    funcionarios.add(new Funcionario(id, nome, cpf, cargo));
                    funcionarioId = Math.max(funcionarioId, id + 1);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao carregar funcionarios: " + e.getMessage());
        }
    }

    private static void carregarCargosDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCargos))) {
            String linha = reader.readLine(); // Pula o cabeçalho
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String descricao = dados[2];
                double salario = Double.parseDouble(dados[3].trim());
                cargos.add(new Cargo(id, nome, descricao, salario));
                cargoId = Math.max(cargoId, id + 1);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao carregar cargos: " + e.getMessage());
        }
    }

    private static void carregarRegistroPontoDoArquivo() {
      try (BufferedReader reader = new BufferedReader(new FileReader(arquivoPontos))) {
          String linha = reader.readLine(); // Pula o cabeçalho
          while ((linha = reader.readLine()) != null) {
              String[] dados = linha.split(";");
              int id = Integer.parseInt(dados[0]);
              int idFuncionario = Integer.parseInt(dados[1].trim());
              Funcionario funcionario = buscarFuncionarioPorId(idFuncionario);
              String data = dados[2];
              String horario = dados[3];
              char categoria = dados[4].charAt(0);
              registrosPontos.add(new RegistroPonto(id, funcionario, data, horario, categoria));
              registroPontosId = Math.max(cargoId, id + 1);
          }
      } catch (IOException | NumberFormatException e) {
          System.out.println("Erro ao carregar Registros: " + e.getMessage());
      }
  }

    private static void salvarFuncionariosNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoFuncionarios))) {
            writer.write("Id;Nome;Cpf;Cargo\n");
            for (Funcionario f : funcionarios) {
                writer.write(f.getId() + ";" + f.getNome() + ";" + f.getCpf() + ";" + f.getCargo().getId() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar funcionrios: " + e.getMessage());
        }
    }

    private static void salvarCargosNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoCargos))) {
            writer.write("Id;Nome;Descricao;Salario\n");
            for (Cargo c : cargos) {
                writer.write(c.getId() + ";" + c.getNome() + ";" + c.getDescricao() + ";" + c.getSalario() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar cargos: " + e.getMessage());
        }
    }

    private static void salvarRegistrosNoArquivo() {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoPontos))) {
          writer.write("Id;Funcionario;Data;Horario;Categoria\n");
          for (RegistroPonto r : registrosPontos) {
              writer.write(r.getId() + ";" + r.getFuncionario().getId() + ";" + r.getData() + ";" + r.getHorario() + ";" + r.getCategoria() + "\n");
          }
      } catch (IOException e) {
          System.out.println("Erro ao salvar Registros de ponto: " + e.getMessage());
      }
  }
    
}