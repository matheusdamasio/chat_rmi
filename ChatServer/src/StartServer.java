import java.rmi.*;
import java.rmi.server.*;

public class StartServer {
    public static void main(String[] args) {
        try{
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            
            ChatServerInt b=new ChatServer();
            Naming.rebind("rmi://10.62.136.111/myabc", b);
            System.out.println("[System] Chat está funcionando");
        }
        catch (Exception e) {
            System.out.println("Conecção com Chat falhou: " + e);
        }
    }
}
