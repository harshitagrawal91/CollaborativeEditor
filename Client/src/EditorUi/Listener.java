package EditorUi;

import clientObjects.Identifier;
import clientObjects.InsertMessage;
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
import clientObjects.clientInitalizer;
import java.util.HashMap;
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
    HashMap<Double, InsertMessage> messageBuffer = new HashMap<>();

    Listener(ObjectInputStream in, ObjectOutputStream out, Socket s) {
        this.in = in;
        this.out = out;
        this.s = s;
    }

    public void run() {
        System.out.print("listener started");
        while (true) {
            try {
                Object obj = in.readObject();
                if (obj instanceof clientInitalizer) {
                    System.out.println("in listener.java");
                    clientInitalizer clientInit = (clientInitalizer) obj;
                    GlobalConstants.documentName = new StringBuffer(clientInit.getDocumentName());
                    GlobalConstants.text = new StringBuffer(clientInit.getText());
                    GlobalConstants.clientId.set(clientInit.getClientId());
                    GlobalConstants.positionList.addAll(clientInit.getPositionList());
                    GlobalConstants.doublepositionList.addAll(clientInit.getDoublePositionList());
                } else if (obj instanceof InsertMessage) {
                    InsertMessage im = (InsertMessage) obj;
                    handleInsertMessage(im);
                }
            } catch (IOException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void handleInsertMessage(InsertMessage im) {
        if(im.getActualPosition()==0 && im.isUpdate()==true){
            
        }
        if (im.getPosition().getSiteId() == GlobalConstants.clientId.get()) {
            if(im.getActualPosition()==0 && im.isUpdate() ){
                if(GlobalConstants.doublepositionList.size()>1){
                    im.setActualPosition(im.getActualPosition()+1);
                }
            }
//            if (im.getActualPosition() == 0 && im.isUpdate() == false) {
//                int index = GlobalConstants.doublepositionList.indexOf(im.getPosition().getRelativePosition());
//                if (index == -1) {
//                    GlobalConstants.doublepositionList.add(im.getActualPosition(), im.getPosition().getRelativePosition());
//                    GlobalConstants.positionList.add(im.getActualPosition(), im.getPosition());
//                    GlobalConstants.text.insert(im.getActualPosition(), im.getCharacter());
//                    GlobalConstants.editWin.insertCharacter(Character.toString(im.getCharacter()), im.getActualPosition());
//                } else {
//                    messageBuffer.put(im.getPosition().getRelativePosition(), im);
//                }
//
//            } else {
                int actualPos = im.getActualPosition();
                double newRelative = im.getPosition().getRelativePosition();
                double oldRelative = GlobalConstants.doublepositionList.get(actualPos);
                GlobalConstants.doublepositionList.set(actualPos, newRelative);
                GlobalConstants.positionList.set(actualPos, im.getPosition());
                if (messageBuffer.containsKey(oldRelative)) {
                    handleInsertMessage(messageBuffer.get(oldRelative));
                }
//            }

        } else {
            int actualPos = im.getActualPosition();
            if(im.isUpdate()){
                 double oldRelative = GlobalConstants.doublepositionList.get(im.getActualPosition());
                GlobalConstants.doublepositionList.set(actualPos, im.getPosition().getRelativePosition());
                GlobalConstants.positionList.get(im.getActualPosition()).setRelativePosition(im.getPosition().getRelativePosition());
                 if (messageBuffer.containsKey(oldRelative)) {
                    handleInsertMessage(messageBuffer.get(oldRelative));
                }
            }
            double relativePos = im.getPosition().getRelativePosition();
            int index = GlobalConstants.doublepositionList.indexOf(relativePos);
            if (index == -1) {
                GlobalConstants.doublepositionList.add(actualPos, relativePos);
                GlobalConstants.positionList.add(actualPos, im.getPosition());
                GlobalConstants.text.insert(actualPos, im.getCharacter());
                GlobalConstants.editWin.insertCharacter(Character.toString(im.getCharacter()), actualPos);
            } else {
                messageBuffer.put(relativePos, im);
            }

        }
    }
}
