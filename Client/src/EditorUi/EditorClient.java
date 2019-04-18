package EditorUi;



import clientObjects.Identifier;
import clientObjects.SyncMessage;
import constants.GlobalConstants;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

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
   String serverIp;
    public EditorClient(int port, String serverIp) throws IOException {
      this.port=port; 
      this.serverIp=serverIp;
    }
    public void connectToServer(){
        try{
           Socket socket = new Socket(serverIp,port);
           ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
           os.flush();
           ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
            System.out.print("connected to server");
            GlobalConstants.listener = new Listener(in, os, socket);
            GlobalConstants.listener.start();
            try {
            TimeUnit.SECONDS.sleep(2);
            }
            catch(Exception e) {
            	e.getMessage();
            }
            Writer w=new Writer(in, os, socket);
            w.start();
            GlobalConstants.writer = w;
            //GlobalConstants.writer.start();            
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
