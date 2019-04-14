package EditorUi;



import clientObjects.Identifier;
import clientObjects.InsertMessage;
import constants.GlobalConstants;
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
            final Socket socket = new Socket("localhost",port);
          final  ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
            os.flush();
           final  ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
            System.out.print("connected to server");
            GlobalConstants.listener = new Listener(in, os, socket);
            GlobalConstants.listener.start();
            Identifier identifier = new Identifier();
                    identifier.setActualPosition(0);
                    identifier.setRelativePosition(0);
                    identifier.setSiteId(GlobalConstants.clientId.get());
                    //GlobalConstants.positionList.add(0, identifier);
                    InsertMessage im = new InsertMessage();
                    im.setPosition(identifier);
                    im.setCharacter('c');
                    im.setActualPosition(0);
                    try {
            os.writeObject(im);
            os.flush();
//			System.out.println("Send message: " + msg + " to Client " + no);
        } catch (IOException ioException) {
          System.out.print("error");
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
