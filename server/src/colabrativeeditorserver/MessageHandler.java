/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colabrativeeditorserver;

import java.util.concurrent.ConcurrentLinkedQueue;
import clientObjects.Identifier;
import clientObjects.SyncMessage;
import constants.GlobalConstants;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author harsh
 */
public class MessageHandler extends Thread {

    public ConcurrentLinkedQueue<SyncMessage> incomingMessageQueue = new ConcurrentLinkedQueue<SyncMessage>();
    ConcurrentHashMap<Identifier,Integer > deletebuffer = new ConcurrentHashMap<>();

    public void run() {

        while (true) {
            if (!incomingMessageQueue.isEmpty()) {
                SyncMessage message = incomingMessageQueue.poll();
                if (message.getType() == GlobalConstants.messageType.INSERT.getValue()) {
                     System.out.println("insert"+message.getType());
                    handleInsert(message);
                } else if (message.getType() == GlobalConstants.messageType.DELETE.getValue()) {
                     System.out.println("delete"+message.getType());
                    handleDelete(message);
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

    private void handleDelete(SyncMessage message) {
         System.out.println("delete");
        int index = GlobalConstants.doublepositionList.indexOf(message.getPosition().getRelativePosition());
        if (index == -1 || GlobalConstants.positionList.get(index).getSiteId()!=message.getPosition().getSiteId()) {
            deletebuffer.put( message.getPosition(),-1);
        } else {
            GlobalConstants.doublepositionList.remove(index);
            GlobalConstants.positionList.remove(index);
            GlobalConstants.text.deleteCharAt(index);
            GlobalConstants.broadcast.execute(new BroadcasterThread(message));
        }
         System.out.println(GlobalConstants.text);
    }

    private void handleInsert(SyncMessage message) {
         System.out.println("insert");
        double relativePos = message.getPosition().getRelativePosition();
        int actualPos = message.getActualPosition();
        int index = GlobalConstants.doublepositionList.indexOf(relativePos);
        if (index == -1) {
            try {
                Double valueFromDoubleMap = GlobalConstants.doublepositionList.get(actualPos);
                Double prev = GlobalConstants.doublepositionList.get(actualPos - 1);
                double newRelative = (valueFromDoubleMap + prev) / 2;
                message.getPosition().setRelativePosition(newRelative);
                GlobalConstants.doublepositionList.add(actualPos, newRelative);
                GlobalConstants.text.insert(actualPos, message.getCharacter());
                GlobalConstants.positionList.add(actualPos, message.getPosition());
                GlobalConstants.broadcast.execute(new BroadcasterThread(message));
                if(deletebuffer.contains(message.getPosition())){
                    handleDelete(message);
                }

            } catch (IndexOutOfBoundsException i) {
                GlobalConstants.doublepositionList.add(actualPos, relativePos);
                GlobalConstants.positionList.add(actualPos, message.getPosition());
                GlobalConstants.text.insert(actualPos, message.getCharacter());
                GlobalConstants.broadcast.execute(new BroadcasterThread(message));
                if(deletebuffer.contains(message.getPosition())){
                    handleDelete(message);
                }

            }
        } else {
            if (actualPos == 0) {
                Double next = GlobalConstants.doublepositionList.get(actualPos + 1);
                double newRelativePos = (relativePos + next) / 2;
                GlobalConstants.doublepositionList.add(actualPos, relativePos);
                GlobalConstants.positionList.add(actualPos, message.getPosition());
                GlobalConstants.text.insert(actualPos, message.getCharacter());
                GlobalConstants.doublepositionList.set(actualPos + 1, relativePos);
                GlobalConstants.positionList.get(actualPos + 1).setRelativePosition(newRelativePos);
                SyncMessage update = new SyncMessage();
                update.setActualPosition(actualPos);
                update.setUpdate(true);
                Identifier id = new Identifier();
                id.setRelativePosition(newRelativePos);
                id.setSiteId(message.getPosition().getSiteId());
                update.setPosition(id);
                GlobalConstants.broadcast.execute(new BroadcasterThread(update));
                GlobalConstants.broadcast.execute(new BroadcasterThread(message));
                if(deletebuffer.contains(message.getPosition())){
                    handleDelete(message);
                }
                if(deletebuffer.contains(update.getPosition())){
                    handleDelete(update);
                }

            } else {
                Double next = GlobalConstants.doublepositionList.get(actualPos + 1);
                double newRelativePos = (relativePos + next) / 2;
                message.getPosition().setRelativePosition(newRelativePos);
                message.setActualPosition(actualPos + 1);
                GlobalConstants.doublepositionList.add(actualPos + 1, newRelativePos);
                GlobalConstants.positionList.add(actualPos + 1, message.getPosition());
                GlobalConstants.text.insert(actualPos + 1, message.getCharacter());
                GlobalConstants.broadcast.execute(new BroadcasterThread(message));
                if(deletebuffer.contains(message.getPosition())){
                    handleDelete(message);
                }
            }
        }
        System.out.println(GlobalConstants.text);
    }

}
