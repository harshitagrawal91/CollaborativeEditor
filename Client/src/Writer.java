
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dell
 */
public class Writer extends Thread {
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket s;
    ConcurrentLinkedQueue<Object> message=new ConcurrentLinkedQueue<>();
    Writer(ObjectInputStream in,ObjectOutputStream out,Socket s){
        this.in=in;
        this.out=out;
        this.s=s;
    }
    public void run(){
        System.out.print("listener started");
       while(!message.isEmpty()){
           
       }
    }
}
