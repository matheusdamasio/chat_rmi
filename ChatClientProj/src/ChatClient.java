import java.io.File;
import java.io.FileOutputStream;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
 
public class ChatClient  extends UnicastRemoteObject implements ChatClientInt{
    
    private static final long serialVersionUID = 1L;
    private String name;
    private ChatUI ui;	
    public ChatClient (String n) throws RemoteException {
        super();
        name=n;
    }

    public void tell(String st) throws RemoteException{
        System.out.println(st);
        ui.writeMsg(st);
    }
    
    public String getName() throws RemoteException{
            return name;
    }

    public void setGUI(ChatUI t){ 
            ui=t ; 
    } 	

    public boolean sendData(String filename, byte[] data, int len) throws RemoteException{
        try{
            File f=new File(filename);
            f.createNewFile();
            FileOutputStream out=new FileOutputStream(f,true);
            out.write(data,0,len);
            out.flush();
            out.close();
            System.out.println("Done writing data...");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
}