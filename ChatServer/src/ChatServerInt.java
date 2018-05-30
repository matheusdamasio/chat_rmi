import java.rmi.*;
import java.util.*;

public interface ChatServerInt extends Remote {
    public boolean login (ChatClientInt a) throws RemoteException;
    public void publish (String s) throws RemoteException;
    public void publishPM (String s, ChatClientInt user1, ChatClientInt user2 ) throws RemoteException;
    public Vector getConnect() throws RemoteException;
    
}
