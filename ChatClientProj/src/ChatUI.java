import javax.swing.*;
import javax.swing.border.*;
 
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.util.*;
 
public class ChatUI{
    private ChatClient client;
    private ChatServerInt server;
    public void doConnect(){
        if (connect.getText().equals("Conectar")){
            if (name.getText().length()<2){JOptionPane.showMessageDialog(frame, "Nome é obrigatório."); return;}
            if (ip.getText().length()<2){JOptionPane.showMessageDialog(frame, "IP é obrigatório."); return;}	    	
            try{
                client=new ChatClient(name.getText());
                client.setGUI(this);
                server=(ChatServerInt)Naming.lookup("rmi://"+ip.getText()+"/myabc");
                server.login(client);
                updateUsers(server.getConnect());
                connect.setText("Disconectar");			    
            }catch(Exception e){e.printStackTrace();JOptionPane.showMessageDialog(frame, "Não foi possível conectar");}		  
        } 
        else{
            updateUsers(null);
            connect.setText("Conectar");
            //Better to implement Logout ....
        }
    }   
  
    public void sendText(){
        if (connect.getText().equals("Conectar")){
            JOptionPane.showMessageDialog(frame, "É necessário estar logado antes"); return;	
        }
        String st=tf.getText();
        st="["+name.getText()+"] "+st;
        tf.setText("");
        //Remove if you are going to implement for remote invocation
        try{
            server.publish(st);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
 
    public void writeMsg(String st){  
        tx.setText(tx.getText()+"\n"+st);  
    }
 
    public void updateUsers(Vector v){
        DefaultListModel listModel = new DefaultListModel();
        if(v!=null) for (int i=0;i<v.size();i++){
            try{  
                String tmp=((ChatClientInt)v.get(i)).getName();
                listModel.addElement(tmp);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        lst.setModel(listModel);
    }
  
    public static void main(String [] args){
        
        try{
            System.out.println("Hello World !");
            ChatUI c=new ChatUI();
            ChatClient d=new ChatClient("Sepultura");			
            ChatServerInt server=(ChatServerInt)Naming.lookup("rmi://192.168.1.103:1099/myabc");
            server.login(d);
            System.out.println("Listening.....");			
            Scanner s=new Scanner(System.in);			
            while(true){
                String line=s.nextLine();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }  
  
  //User Interface code.
    public ChatUI(){
        frame=new JFrame("Chat da Galera");
        JPanel main =new JPanel();
        JPanel top =new JPanel();
        JPanel cn =new JPanel();
        JPanel bottom =new JPanel();
        ip=new JTextField();
        tf=new JTextField();
        name=new JTextField();
        tx=new JTextArea();
        connect=new JButton("Conectar");
        JButton bt=new JButton("Enviar");
        lst=new JList();        
        main.setLayout(new BorderLayout(5,5));         
        top.setLayout(new GridLayout(1,0,5,5));   
        cn.setLayout(new BorderLayout(5,5));
        bottom.setLayout(new BorderLayout(5,5));
        top.add(new JLabel("Nome: "));top.add(name);    
        top.add(new JLabel("Endereço do Servidor: "));top.add(ip);
        top.add(connect);
        cn.add(new JScrollPane(tx), BorderLayout.CENTER);        
        cn.add(lst, BorderLayout.EAST);    
        bottom.add(tf, BorderLayout.CENTER);    
        bottom.add(bt, BorderLayout.EAST);
        main.add(top, BorderLayout.NORTH);
        main.add(cn, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);
        main.setBorder(new EmptyBorder(10, 10, 10, 10) );
        //Events
        connect.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                doConnect();   
            }  
        });
        bt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                sendText();   
            }  
        });
        tf.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                sendText();   
            }  
        });

        frame.setContentPane(main);
        frame.setSize(600,600);
        frame.setVisible(true);  
    }
    JTextArea tx;
    JTextField tf,ip, name;
    JButton connect;
    JList lst;
    JFrame frame;
}
