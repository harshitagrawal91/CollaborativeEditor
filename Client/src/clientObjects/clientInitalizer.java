/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientObjects;

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
    CopyOnWriteArrayList<Double> doublePositionList;

    public CopyOnWriteArrayList<Double> getDoublePositionList() {
        return doublePositionList;
    }

    public void setDoublePositionList(CopyOnWriteArrayList<Double> doublePositionList) {
        this.doublePositionList = doublePositionList;
    }

    public CopyOnWriteArrayList<Identifier> getPositionList() {
        return positionList;
    }

    public void setPositionList(CopyOnWriteArrayList<Identifier> positionList) {
        this.positionList=positionList;
    }

    public clientInitalizer(int ClientId, String documentName, StringBuffer text,CopyOnWriteArrayList<Identifier> positionList,CopyOnWriteArrayList<Double> doublePositionList) {
        this.ClientId = ClientId;
        this.documentName = documentName;
        this.text = text;
        this.positionList=positionList;
        this.doublePositionList=doublePositionList;
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
