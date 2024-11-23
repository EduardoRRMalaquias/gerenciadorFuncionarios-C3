import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static String arquivoFuncionarios = "src/dados/Funcionarios.csv";
    private static String arquivoCargos = "src/dados/Cargos.csv";
    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static List<Cargo> cargos = new ArrayList<>();
    private static int funcionarioId = 1;
    private static int cargoId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        carregarCargosDoArquivo();
        carregarFuncionariosDoArquivo();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Gerenciar Funcionários");
            System.out.println("2. Gerenciar Cargos");
            System.out.println("3. Consultar Histórico de Funcionários");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    gerenciarFuncionarios(scanner);
                    break;
                case 2:
                    gerenciarCargos(scanner);
                    break;
                case 3:
                    consultarFuncionarios();
                    break;
                case 4:
                    System.out.println("Encerrando...");
                    salvarCargosNoArquivo();
                    salvarFuncionariosNoArquivo();
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void gerenciarFuncionarios(Scanner scanner) {
        System.out.println("\nGerenciar Funcionários:");
        System.out.println("1. Cadastrar");
        System.out.println("2. Editar");
        System.out.println("3. Consultar");
        System.out.println("4. Apagar");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                cadastrarFuncionario(scanner);
                break;
            case 2:
                editarFuncionario(scanner);
                break;
            case 3:
                consultarFuncionarios();
                break;
            case 4:
                apagarFuncionario(scanner);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void gerenciarCargos(Scanner scanner) {
        System.out.println("\nGerenciar Cargos:");
        System.out.println("1. Cadastrar");
        System.out.println("2. Editar");
        System.out.println("3. Consultar");
        System.out.println("4. Consultar Cargos com IDs");
        System.out.println("5. Apagar");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                cadastrarCargo(scanner);
                break;
            case 2:
                editarCargo(scanner);
                break;
            case 3:
                consultarCargos();
                break;
            case 4:
                consultarCargosComIds();
                break;
            case 5:
                apagarCargo(scanner);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    // Métodos para Funcionários
    private static void cadastrarFuncionario(Scanner scanner) {
        scanner.nextLine(); // Limpa o buffer após o último nextInt()
        System.out.print("Nome do funcionário: ");
        String nome = scanner.nextLine(); // Captura o nome completo
        System.out.print("CPF do funcionário: ");
        String cpf = scanner.next();
    
        if (cpfExiste(cpf)) {
            System.out.println("Erro: CPF já cadastrado!");
            return;
        }
    
        consultarCargosComIds();
        System.out.print("ID do cargo: ");
        int idCargo = scanner.nextInt();
        Cargo cargo = buscarCargoPorId(idCargo);
        if (cargo != null) {
            funcionarios.add(new Funcionario(funcionarioId++, nome, cpf, cargo));
            System.out.println("Funcionário cadastrado!");
        } else {
            System.out.println("Cargo não encontrado!");
        }
    }
    

    private static void editarFuncionario(Scanner scanner) {
        System.out.print("ID do funcionário: ");
        int id = scanner.nextInt();
        Funcionario func = buscarFuncionarioPorId(id);
        if (func != null) {
            System.out.print("Novo nome: ");
            func.setNome(scanner.next());
            System.out.print("Novo CPF: ");
            String novoCpf = scanner.next();

            if (!func.getCpf().equals(novoCpf) && cpfExiste(novoCpf)) {
                System.out.println("Erro: CPF já cadastrado!");
                return;
            }

            func.setCpf(novoCpf);
            consultarCargosComIds();
            System.out.print("Novo ID do cargo: ");
            int novoCargoId = scanner.nextInt();
            Cargo novoCargo = buscarCargoPorId(novoCargoId);
            if (novoCargo != null) {
                func.setCargo(novoCargo);
                System.out.println("Funcionário atualizado!");
            } else {
                System.out.println("Cargo não encontrado!");
            }
        } else {
            System.out.println("Funcionário não encontrado!");
        }
    }

    private static void consultarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        System.out.println("\nLista de Funcionários:");
        funcionarios.forEach(f -> System.out.println(f.getId() + " - " + f.getNome() + " - " + f.getCpf() + " - " + f.getCargo().getNome()));
    }

    private static void apagarFuncionario(Scanner scanner) {
        System.out.print("ID do funcionário: ");
        int idApagar = scanner.nextInt();
        funcionarios.removeIf(f -> f.getId() == idApagar);
        System.out.println("Funcionário removido!");
    }

    // Métodos para Cargos
    private static void cadastrarCargo(Scanner scanner) {
        System.out.print("Nome do cargo: ");
        String nome = scanner.next();
        System.out.print("Salário do cargo: ");
        double salario = scanner.nextDouble();
        cargos.add(new Cargo(cargoId++, nome, salario));
        System.out.println("Cargo cadastrado!");
    }

    private static void editarCargo(Scanner scanner) {
        System.out.print("ID do cargo: ");
        int id = scanner.nextInt();
        Cargo cargo = buscarCargoPorId(id);
        if (cargo != null) {
            System.out.print("Novo nome: ");
            cargo.setNome(scanner.next());
            System.out.print("Novo salário: ");
            cargo.setSalario(scanner.nextDouble());
            System.out.println("Cargo atualizado!");
        } else {
            System.out.println("Cargo não encontrado!");
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
        System.out.println("\nLista de Cargos (ID - Nome - Salário):");
        cargos.forEach(c -> System.out.println(c.getId() + " - " + c.getNome() + " - R$ " + c.getSalario()));
    }

    private static void apagarCargo(Scanner scanner) {
        System.out.print("ID do cargo: ");
        int idApagar = scanner.nextInt();
        cargos.removeIf(c -> c.getId() == idApagar);
        System.out.println("Cargo removido!");
    }

    // Métodos auxiliares
    private static Funcionario buscarFuncionarioPorId(int id) {
        return funcionarios.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }

    private static Cargo buscarCargoPorId(int id) {
        return cargos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
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
            System.out.println("Erro ao carregar funcionários: " + e.getMessage());
        }
    }

    private static void carregarCargosDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCargos))) {
            String linha = reader.readLine(); // Pula o cabeçalho
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                double salario = Double.parseDouble(dados[2].trim());
                cargos.add(new Cargo(id, nome, salario));
                cargoId = Math.max(cargoId, id + 1);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao carregar cargos: " + e.getMessage());
        }
    }

    private static void salvarFuncionariosNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoFuncionarios))) {
            writer.write("Id;Nome;Cpf;Cargo\n");
            for (Funcionario f : funcionarios) {
                writer.write(f.getId() + ";" + f.getNome() + ";" + f.getCpf() + ";" + f.getCargo().getId() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar funcionários: " + e.getMessage());
        }
    }

    private static void salvarCargosNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoCargos))) {
            writer.write("Id;Nome;Salario\n");
            for (Cargo c : cargos) {
                writer.write(c.getId() + ";" + c.getNome() + ";" + c.getSalario() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar cargos: " + e.getMessage());
        }
    }
}
