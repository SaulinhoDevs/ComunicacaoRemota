import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Pedido extends Remote {
    String listarCardapio() throws RemoteException;
    String fazerPedido(String cliente, String item) throws RemoteException;
    void salvarPedidosEmArquivo() throws RemoteException;
}