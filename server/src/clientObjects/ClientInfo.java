/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientObjects;

import colabrativeeditorserver.ClientHandler;

/**
 *
 * @author harsh
 */
public class ClientInfo {
   private int uniqueId;
   private ClientHandler clientHandler;

    public ClientInfo(int uniqueId,ClientHandler clientHandler) {
        this.uniqueId=uniqueId;
        this.clientHandler=clientHandler;
    }
   

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }
}
