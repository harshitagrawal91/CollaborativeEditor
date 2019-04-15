/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colabrativeeditorserver;

import java.util.concurrent.ConcurrentLinkedQueue;
import clientObjects.Identifier;
import clientObjects.InsertMessage;
import constants.GlobalConstants;

/**
 *
 * @author harsh
 */
public class MessageHandler extends Thread {

    public ConcurrentLinkedQueue<InsertMessage> insertMessageQueue = new ConcurrentLinkedQueue<InsertMessage>();
//    public ConcurrentLinkedQueue<DeleteMessage> deletemessageQueue = new ConcurrentLinkedQueue<DeleteMessage>();

    public void run() {
        while (true) {
            if (!insertMessageQueue.isEmpty()) {
                InsertMessage message = insertMessageQueue.poll();
                double relativePos = message.getPosition().getRelativePosition();
                int actualPos = message.getActualPosition();
                //System.out.print(message.getCharacter());
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

                    } catch (IndexOutOfBoundsException i) {
                        GlobalConstants.doublepositionList.add(actualPos, relativePos);
                        GlobalConstants.positionList.add(actualPos, message.getPosition());
                        GlobalConstants.text.insert(actualPos, message.getCharacter());
                        GlobalConstants.broadcast.execute(new BroadcasterThread(message));

                    }
                } else {
                    Double next = GlobalConstants.doublepositionList.get(actualPos + 1);
                    double newRelativePos = (relativePos + next) / 2;
                    message.getPosition().setRelativePosition(newRelativePos);
                    GlobalConstants.doublepositionList.add(actualPos + 1, newRelativePos);
                    GlobalConstants.positionList.add(actualPos + 1, message.getPosition());
                    GlobalConstants.text.insert(actualPos + 1, message.getCharacter());
                    GlobalConstants.broadcast.execute(new BroadcasterThread(message));
                }
                 System.out.println(GlobalConstants.text);
            } //            else if(false){
            //            }
            else {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException ex) {

                    }
                }
            }
        }
    }

}
