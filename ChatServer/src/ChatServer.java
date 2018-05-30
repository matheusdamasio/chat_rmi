import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ChatServer extends UnicastRemoteObject implements ChatServerInt{
    private Vector listaUser=new Vector();
    public ChatServer() throws RemoteException{}
    
    public boolean login(ChatClientInt a) throws RemoteException{
        System.out.println(a.getName() + " se conectou...");
        a.tell("Você se conectou com sucesso.");
        publish(a.getName()+ " acabou de se conectar.");
        listaUser.add(a);
        return true;
    }
    
    public void publish(String s) throws RemoteException{
        System.out.println(s);
            for(int i=0;i<listaUser.size();i++){
                try{
                    ChatClientInt tmp=(ChatClientInt)listaUser.get(i);
                    tmp.tell(s);
                }
                catch(Exception e){
                
                }
            }
    }
    
    public void publishPM (String s, ChatClientInt user1, ChatClientInt user2) throws RemoteException{
        for(int i=0;i<listaUser.size();i++){
            ChatClientInt tmp=(ChatClientInt)listaUser.get(i);
        }
    }
    
    public Vector getConnect() throws RemoteException {
        return listaUser;
    }

}
