import java.rmi.*;

public interface ChatClientInt extends Remote{
    public void tell(String name) throws RemoteException;
    public boolean sendData(String filename, byte[] data, int len) throws RemoteException;
    public String getName() throws RemoteException;
    
}
