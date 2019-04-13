package EditorUi;



import objects.Identifier;
import constants.GlobalConstants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.clientInitalizer;
//import Objects.clientInitalizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dell
 */
public class Listener extends Thread {
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket s;
    Listener(ObjectInputStream in,ObjectOutputStream out,Socket s){
        this.in=in;
        this.out=out;
        this.s=s;
    }
    public void run(){
        System.out.print("listener started");
        while(true){
            try {
                Object obj=in.readObject();
                if(obj instanceof clientInitalizer){
                    clientInitalizer clientInit=(clientInitalizer)obj;
                    GlobalConstants.documentName=new StringBuffer(clientInit.getDocumentName());
                    GlobalConstants.text=new StringBuffer(clientInit.getText());
                    GlobalConstants.clientId.set(clientInit.getClientId());
                    GlobalConstants.positionList.addAll(clientInit.getPositionList());
                }
            } catch (IOException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
