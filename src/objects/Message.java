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


public class Message implements Serializable {
   private long uniqueId;
   private String fileName;
   private StringBuffer text;

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

    public StringBuffer getText() {
        return text;
    }

    public void setText(String text) {
        this.text = new StringBuffer(text);
    }
   
}
