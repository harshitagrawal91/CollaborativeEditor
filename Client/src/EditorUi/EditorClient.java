package EditorUi;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dell
 */
public class EditorClient {
   int port;
    public EditorClient(int port) throws IOException {
      this.port=port; 
    }
    public void connectToServer(){
        try{
            Socket socket = new Socket("localhost",port);
            ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
            os.flush();
            ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
            System.out.print("connected to server");
            new Listener(in, os, socket).start();
            new Writer(in, os, socket).start();
        }catch(IOException e){
            
        }
    }
    public void write() throws IOException
    {
        out.writeUTF("test string to check connection");
    }
    
    private Socket socket = null;
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null;
}
