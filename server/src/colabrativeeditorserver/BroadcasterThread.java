/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colabrativeeditorserver;

import clientObjects.InsertMessage;
import clientObjects.ClientInfo;
import constants.GlobalConstants;

/**
 *
 * @author harsh
 */
public class BroadcasterThread implements Runnable{
   InsertMessage message;
    public BroadcasterThread(InsertMessage message) {
        this.message=message;
    } 
    public void run(){
        for(int clientid : GlobalConstants.clientList.keySet()){
            ClientInfo client=GlobalConstants.clientList.get(clientid);
            client.getClientHandler().sendMessage(this.message);
        }
    }
}
