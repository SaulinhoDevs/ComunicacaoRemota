import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.*;
import java.io.*;

public class PedidoImpl extends UnicastRemoteObject implements Pedido {
    private Map<Integer, String> cardapio;
    private List<String> pedidosFeitos;
    private int contador = 1;

    protected PedidoImpl() throws RemoteException {
        super();
        cardapio = new HashMap<>();
        pedidosFeitos = new ArrayList<>();
        cardapio.put(1, "Hambúrguer");
        cardapio.put(2, "Pizza");
        cardapio.put(3, "Refrigerante");
        cardapio.put(4, "Batata Frita");
    }

    public String listarCardapio() {
        StringBuilder sb = new StringBuilder("Cardápio:\n");
        for (Map.Entry<Integer, String> item : cardapio.entrySet()) {
            sb.append(item.getKey()).append(" - ").append(item.getValue()).append("\n");
        }
        return sb.toString();
    }

    public String fazerPedido(String cliente, String item) {
        String pedido = "Pedido #" + contador++ + " de " + cliente + ": " + item;
        pedidosFeitos.add(pedido);
        System.out.println("[SERVIDOR] " + pedido);
        return pedido + " confirmado!";
    }

    public void salvarPedidosEmArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pedidos_do_dia.txt"))) {
            writer.write("Relatório de Pedidos do Dia\n");
            writer.write("===========================\n");
            for (String pedido : pedidosFeitos) {
                writer.write(pedido + "\n");
            }
            System.out.println("[SERVIDOR] Pedidos salvos em 'pedidos_do_dia.txt'");
        } catch (IOException e) {
            System.err.println("Erro ao salvar pedidos: " + e.getMessage());
        }
    }
}