import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ChatServer extends UnicastRemoteObject implements ChatServerInt{
    
    private Vector listaUser=new Vector();
    private String file="";
    
    public ChatServer() throws RemoteException{
        super();
    }
    
    public void setFile(String f){
        file=f;
    }
    
    public boolean login(ChatClientInt a) throws RemoteException{
        
        try{
            System.out.println(a.getName() + " se conectou...");
            a.tell("Você se conectou com sucesso.");
            publish(a.getName()+ " acabou de se conectar.");
            listaUser.add(a);
            
            File f1=new File(file);			 
            FileInputStream in=new FileInputStream(f1);			 				 
            byte [] mydata=new byte[1024*1024];						
            int mylen=in.read(mydata);
            
            while(mylen>0){
                a.sendData(f1.getName(), mydata, mylen);	 
                mylen=in.read(mydata);				 
            }
        }
        catch(Exception e){
            e.printStackTrace();

        }

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
    
    public Vector getConnect() throws RemoteException {
        return listaUser;
    }
}
