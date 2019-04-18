package EditorUi;

import clientObjects.Identifier;
import clientObjects.SyncMessage;
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
    private ConcurrentLinkedQueue<writeQueueMessage> message = new ConcurrentLinkedQueue<writeQueueMessage>();

    Writer(ObjectInputStream in, ObjectOutputStream out, Socket s) {
        this.in = in;
        this.out = out;
        this.s = s;
    }

    public void run() {
        System.out.print("in writer.java\n");
        while (true) {
            if (!getMessage().isEmpty()) {
                writeQueueMessage m = getMessage().poll();
                if (m.getType() == GlobalConstants.messageType.INSERT.getValue()) {
                    System.out.println("insert");
                    insert(m);

                } else if (m.getType() == GlobalConstants.messageType.DELETE.getValue()) {
                    System.out.println("delete");
                    delete(m);
                }
            } else {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException ex) {

                    }
                }
            }
        }
    }

    private void insert(writeQueueMessage m) {
        // TODO Auto-generated method stub
        int pos = m.getPosition();
        char ch = m.getCh().charAt(0);
        int ascii = (int) ch;
        if (ascii == 13 || ascii == 10 || ascii >= 32 && ascii <= 126) {
            if (ascii == 13 || ascii == 10) {
                ch = '\n';
            }
            if (pos == 0) {
                if (GlobalConstants.doublepositionList.contains(0.0)) {
                    double nextrelative = GlobalConstants.doublepositionList.get(pos + 1) / 2f;
                    GlobalConstants.doublepositionList.set(pos, nextrelative);
                    GlobalConstants.positionList.get(pos).setRelativePosition(nextrelative);
                }
                System.out.print("position--" + pos);
                GlobalConstants.doublepositionList.add(pos, (double) pos);
                GlobalConstants.text.insert(pos, ch);
                Identifier identifier = new Identifier();
                identifier.setRelativePosition(pos);
                identifier.setSiteId(GlobalConstants.clientId.get());
                GlobalConstants.positionList.add(pos, identifier);
                SyncMessage im = new SyncMessage();
                im.setPosition(identifier);
                im.setCharacter(ch);
                im.setActualPosition(pos);
                im.setType(GlobalConstants.messageType.INSERT.getValue());
                sendMessage(im);
            } else {
                try {
                    Double valueFromDoubleMap = GlobalConstants.doublepositionList.get(pos);
                    double relative = (GlobalConstants.doublepositionList.get(pos - 1) + valueFromDoubleMap) / 2f;
                    Identifier identifier = new Identifier();
                    identifier.setRelativePosition(relative);
                    identifier.setSiteId(GlobalConstants.clientId.get());
                    GlobalConstants.positionList.add(pos, identifier);
                    GlobalConstants.doublepositionList.add(pos, relative);
                    GlobalConstants.text.insert(pos, ch);
                    SyncMessage im = new SyncMessage();
                    im.setPosition(identifier);
                    im.setCharacter(ch);
                    im.setActualPosition(pos);
                    im.setType(GlobalConstants.messageType.INSERT.getValue());
                    sendMessage(im);
                } catch (IndexOutOfBoundsException i) {
                    Identifier identifier = new Identifier();
                    double relative = (GlobalConstants.doublepositionList.get(pos - 1)) + 1;
                    identifier.setRelativePosition(relative);
                    identifier.setSiteId(GlobalConstants.clientId.get());
                    GlobalConstants.positionList.add(pos, identifier);
                    GlobalConstants.doublepositionList.add(pos, relative);
                    GlobalConstants.text.insert(pos, ch);
                    SyncMessage im = new SyncMessage();
                    im.setPosition(identifier);
                    im.setCharacter(ch);
                    im.setActualPosition(pos);
                    im.setType(GlobalConstants.messageType.INSERT.getValue());
                    sendMessage(im);
                }
            }
        }
    }

    private void delete(writeQueueMessage m) {
        // TODO Auto-generated method stub
        
        int pos = m.getPosition();
        if(pos>=0){
        //System.out.print(m.g);
        Identifier identifier = GlobalConstants.positionList.get(pos);
        char ch = GlobalConstants.text.charAt(pos);
        GlobalConstants.doublepositionList.remove(pos);
        GlobalConstants.text.deleteCharAt(pos);
        GlobalConstants.positionList.remove(pos);
        SyncMessage im = new SyncMessage();
        im.setPosition(identifier);
        im.setCharacter(ch);
        im.setUpdateWindowSiteId(GlobalConstants.clientId.get());
        sendMessage(im);
        }
    }

    public synchronized void sendMessage(Object msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("message sent from writer.java");
            // System.out.println("Send message: " + msg + " to Client " + no);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public ConcurrentLinkedQueue<writeQueueMessage> getMessage() {
        return message;
    }

    public void setMessage(ConcurrentLinkedQueue<writeQueueMessage> message) {
        this.message = message;
    }
}
