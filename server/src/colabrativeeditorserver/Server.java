/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colabrativeeditorserver;

/**
 *
 * @author harsh
 */
import clientObjects.ClientInfo;
import constants.GlobalConstants;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import clientObjects.Identifier;
import clientObjects.clientInitalizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread {

    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private int port;
    private static volatile AtomicLong uniqueID = new AtomicLong(0);
    private static final Object lock = new Object();
    private static volatile Server instance;

    public Server(int port) throws IOException {
        this.port = port;
    }
    public static Server getInstance(int port) {
    Server linstance = instance;
    if (linstance == null) {
        synchronized (lock) {    // While we were waiting for the lock, another 
            linstance = instance;        // thread may have instantiated the object.
            if (linstance == null) {  
                try {
                    linstance = new Server(port);
                     instance = linstance;
                } catch (IOException ex) {
                    instance = linstance;
                }
               
            }
        }
    }
    return linstance;
}
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started at port " + port);
            GlobalConstants.clientId.set(0);
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Client Connection established");
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.flush();
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                MessageHandler mh = new MessageHandler();
                GlobalConstants.messageHandler = mh;
                mh.start();
                clientInitalizer ci = new clientInitalizer(GlobalConstants.clientId.incrementAndGet(), GlobalConstants.documentName.toString(), GlobalConstants.text,
                         GlobalConstants.positionList, GlobalConstants.doublepositionList);
                try {
                    out.writeObject(ci);
                    out.flush();
                    System.out.println("Send Client Initializer");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                ClientHandler ch = new ClientHandler(in, out, socket, GlobalConstants.clientId.get());
                GlobalConstants.clientList.put(GlobalConstants.clientId.get(), new ClientInfo(GlobalConstants.clientId.get(), ch));
                ch.start();

            }
////            DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
//            while(in.available()>0)
//            {
//                System.out.println("writing to the file");
//                out.writeUTF(in.readUTF());
//            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
