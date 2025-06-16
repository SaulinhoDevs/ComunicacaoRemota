import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            Pedido stub = (Pedido) registry.lookup("PedidoService");

            Scanner sc = new Scanner(System.in);
            System.out.print("Digite seu nome: ");
            String nome = sc.nextLine();

            boolean continuar = true;

            while (continuar) {
                System.out.println(stub.listarCardapio());
                System.out.print("Digite o número do item que deseja pedir (ou '0' para sair): ");
                int escolha = sc.nextInt();
                sc.nextLine(); // limpa buffer

                if (escolha == 0) {
                    continuar = false;
                    System.out.println("\nSaindo... Obrigado!");
                    break;
                }

                String item;
                switch (escolha) {
                    case 1: item = "Hambúrguer"; break;
                    case 2: item = "Pizza"; break;
                    case 3: item = "Refrigerante"; break;
                    case 4: item = "Batata Frita"; break;
                    default:
                        System.out.println("\nOpção inválida!\n");
                        continue;
                }

                String resposta = stub.fazerPedido(nome, item);
                System.out.println("\nResposta do servidor: " + resposta);
                System.out.println("Deseja fazer outro pedido? \n");
            }

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
}