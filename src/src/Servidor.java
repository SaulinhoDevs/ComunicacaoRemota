import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        try {
            PedidoImpl obj = new PedidoImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("PedidoService", obj);
            System.out.println("[SERVIDOR] Servidor pronto...");

            // Espera comando para encerrar
            Scanner sc = new Scanner(System.in);
            System.out.println("[SERVIDOR] Digite 'sair' para encerrar e salvar pedidos:");
            while (true) {
                String comando = sc.nextLine();
                if (comando.equalsIgnoreCase("sair")) {
                    obj.salvarPedidosEmArquivo();
                    System.out.println("[SERVIDOR] Encerrando...");
                    break;
                }
            }

        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }
}