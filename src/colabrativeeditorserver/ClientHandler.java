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
import objects.ClientInfo;

/**
 *
 * @author harsh
 */
public class ClientHandler extends Thread {
private ObjectInputStream in;
private ObjectOutputStream out;
private Socket socket;
private final Long uniqueID;
    public ClientHandler(ObjectInputStream in,ObjectOutputStream out,Socket socket,Long uniqueID) {
        this.in=in;
        this.out=out;
        this.socket=socket;
        this.uniqueID=uniqueID;
    } 
    public void run(){
        if(GlobalConstants.getClientMap().get(uniqueID)==null){
             ArrayList<ClientInfo> altemp=new ArrayList<>();
             altemp.add(new ClientInfo(this.uniqueID,"new Document",this));
             GlobalConstants.getClientMap().put(uniqueID,altemp);
        }else{
             ArrayList<ClientInfo> altemp=GlobalConstants.getClientMap().get(uniqueID);
             String docName=altemp.get(0).getFileName();
             altemp.add(new ClientInfo(this.uniqueID,docName,this));         
        }
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
