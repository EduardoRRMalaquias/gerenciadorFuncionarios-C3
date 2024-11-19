public class App {
    public static void main(String[] args) throws Exception {
        Funcionario funcionario = new Funcionario("Eduardo", false);

        System.out.println("BEm vindo funcionario: "+ funcionario.nome);
        if(funcionario.apto){
            System.out.println("Voce é apto");
        }else{
            System.out.println("Voce é inapto");
        }
    }
}
