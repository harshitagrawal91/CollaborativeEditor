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
   private long uniqueId;
   private String fileName;
   private ClientHandler clientHandler;

    public ClientInfo(long uniqueId,String fileName,ClientHandler clientHandler) {
        this.uniqueId=uniqueId;
        this.fileName=fileName;
        this.clientHandler=clientHandler;
    }
   

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }
}
