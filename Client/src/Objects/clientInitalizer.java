/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;

/**
 *
 * @author harsh
 */
public class clientInitalizer implements Serializable{
    int ClientId;
    String documentName;
    StringBuffer text;

    public clientInitalizer(int ClientId, String documentName, StringBuffer text) {
        this.ClientId = ClientId;
        this.documentName = documentName;
        this.text = text;
    }
    

    public int getClientId() {
        return ClientId;
    }

    public void setClientId(int ClientId) {
        this.ClientId = ClientId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public StringBuffer getText() {
        return text;
    }

    public void setText(StringBuffer text) {
        this.text = text;
    }
    
}
