import java.rmi.Naming;
 
public class StartFileServer {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			
			java.rmi.registry.LocateRegistry.createRegistry(4262);
			
			FileServer fs=new FileServer();
			fs.setFile("gg.txt");			
			Naming.rebind("rmi://10.62.136.111:4262/abc", fs);
			System.out.println("File Server is Ready");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}
