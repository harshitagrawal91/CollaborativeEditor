/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colabrativeeditorserver;

import java.util.concurrent.ConcurrentLinkedQueue;
import clientObjects.Identifier;

/**
 *
 * @author harsh
 */
public class MessageHandler extends Thread {
     public ConcurrentLinkedQueue<Identifier> messageQueue = new ConcurrentLinkedQueue<Identifier>();

    public void run() {
        while (true) {
            if (!messageQueue.isEmpty()) {
            }
            }
    }
}
