package EditorUi;

import clientObjects.Identifier;
import clientObjects.InsertMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import clientObjects.writeQueueMessage;
import constants.GlobalConstants;

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
    ConcurrentLinkedQueue<writeQueueMessage> message = new ConcurrentLinkedQueue<writeQueueMessage>();

    Writer(ObjectInputStream in, ObjectOutputStream out, Socket s) {
        this.in = in;
        this.out = out;
        this.s = s;
    }

    public void run() {
        System.out.print("in writer.java\n");
        while (true) {
            if (!message.isEmpty()) {
            	System.out.println("value in message");
                writeQueueMessage m = message.poll();
                int pos = m.getPosition();
                char ch = m.getCh().charAt(0);
                int ascii=(int)ch;
                if(ascii==13 ||ascii==10 || ascii>=32 && ascii<=126 ){
                    if(ascii==13 ||ascii==10){
                        ch='\n';
                    }
                if (pos == 0) {
                    GlobalConstants.doublepositionList.add(pos, (double) pos);
                    GlobalConstants.text.insert(pos, ch);
                    Identifier identifier = new Identifier();
                    identifier.setActualPosition(pos);
                    identifier.setRelativePosition(pos);
                    identifier.setSiteId(GlobalConstants.clientId.get());
                    GlobalConstants.positionList.add(pos, identifier);
                    InsertMessage im = new InsertMessage();
                    im.setPosition(identifier);
                    im.setCharacter(ch);
                    im.setActualPosition(pos);
                    sendMessage(im);
                } else {
                    try {
                        Double valueFromDoubleMap = GlobalConstants.doublepositionList.get(pos);
                        double relative = (GlobalConstants.doublepositionList.get(pos - 1) + valueFromDoubleMap) / 2;
                        Identifier identifier = new Identifier();
                        identifier.setActualPosition(pos);
                        identifier.setRelativePosition(relative);
                        identifier.setSiteId(GlobalConstants.clientId.get());
                        GlobalConstants.positionList.add(pos, identifier);
                        GlobalConstants.doublepositionList.add(pos, relative);
                        GlobalConstants.text.insert(pos, ch);
                        InsertMessage im = new InsertMessage();
                        im.setPosition(identifier);
                        im.setCharacter(ch);
                        im.setActualPosition(pos);
                        sendMessage(im);
                    } catch (IndexOutOfBoundsException i) {
                        Identifier identifier = new Identifier();
                        identifier.setActualPosition(pos);
                        double relative = (GlobalConstants.doublepositionList.get(pos - 1)) + 1;
                        identifier.setRelativePosition(relative);
                        identifier.setSiteId(GlobalConstants.clientId.get());
                        GlobalConstants.positionList.add(pos, identifier);
                        GlobalConstants.doublepositionList.add(pos, relative);
                        GlobalConstants.text.insert(pos, ch);
                        InsertMessage im = new InsertMessage();
                        im.setPosition(identifier);
                        im.setCharacter(ch);
                        im.setActualPosition(pos);
                        sendMessage(im);
                    }
                }
            }
            }else{
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException ex) {

                    }
                }
            }
        }
    }

    public synchronized void sendMessage(Object msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("message sent from writer.java");
			//System.out.println("Send message: " + msg + " to Client " + no);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
