/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colabrativeeditorserver;

import constants.GlobalConstants;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import clientObjects.ClientInfo;
import clientObjects.SyncMessage;

/**
 *
 * @author harsh
 */
public class ClientHandler extends Thread {

    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private final Socket socket;
    private final int clientId;

    public ClientHandler(ObjectInputStream in, ObjectOutputStream out, Socket socket, int uniqueID) {
        this.in = in;
        this.out = out;
        this.socket = socket;
        this.clientId = uniqueID;
    }

    public void run() {
//        if(GlobalConstants.getClientMap().get(clientId)==null){
//             ArrayList<ClientInfo> altemp=new ArrayList<>();
//             altemp.add(new ClientInfo(this.clientId,"new Document",this));
//             GlobalConstants.getClientMap().put(clientId,altemp);
//        }else{
//             ArrayList<ClientInfo> altemp=GlobalConstants.getClientMap().get(clientId);
//             String docName=altemp.get(0).getFileName();
//             altemp.add(new ClientInfo(this.clientId,docName,this));         
//        }
        try {
            while (true) {
                Object obj = in.readObject();
//                 System.out.print("received message\n");
                if (obj instanceof SyncMessage) {
                    GlobalConstants.messageHandler.incomingMessageQueue.add((SyncMessage) obj);
//                    System.out.print("added");
                    if (GlobalConstants.messageHandler.getState().equals(Thread.State.WAITING)) {
                        synchronized (GlobalConstants.messageHandler) {
                            GlobalConstants.messageHandler.notify();
                        }
                    }
                }
            }

        } catch (IOException ex) {
                System.out.print("IO Exception"+ex);
        } catch (ClassNotFoundException ex) {
               System.out.print("Class not found Exception"+ex.getStackTrace());
        }
    }

    public synchronized void sendMessage(Object msg) {
        try {
            out.writeObject(msg);
            out.flush();
//			System.out.println("Send message: " + msg + " to Client " + no);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
