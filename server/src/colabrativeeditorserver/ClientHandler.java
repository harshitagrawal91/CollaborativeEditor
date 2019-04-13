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

/**
 *
 * @author harsh
 */
public class ClientHandler extends Thread {
private ObjectInputStream in;
private ObjectOutputStream out;
private Socket socket;
private final int clientId;
    public ClientHandler(ObjectInputStream in,ObjectOutputStream out,Socket socket,int uniqueID) {
        this.in=in;
        this.out=out;
        this.socket=socket;
        this.clientId=uniqueID;
    } 
    public void run(){
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
       while(true){
           
                Object obj=in.readObject();
           
       }
        
    } catch (IOException ex) {
//                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
}
}
