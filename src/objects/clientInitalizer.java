/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author harsh
 */
public class clientInitalizer implements Serializable{
    int ClientId;
    String documentName;
    StringBuffer text;
    CopyOnWriteArrayList<Identifier> positionList;

    public CopyOnWriteArrayList<Identifier> getPositionList() {
        return positionList;
    }

    public void setPositionList(CopyOnWriteArrayList<Identifier> positionList) {
        this.positionList.addAll(positionList);
    }

    public clientInitalizer(int ClientId, String documentName, StringBuffer text,CopyOnWriteArrayList<Identifier> positionList) {
        this.ClientId = ClientId;
        this.documentName = documentName;
        this.text = text;
        this.positionList=positionList;
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
