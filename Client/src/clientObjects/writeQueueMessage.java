/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientObjects;

import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class writeQueueMessage {

    int position;
    String ch;
    byte type;
    ArrayList<Integer> delPositions=new ArrayList<Integer>();

    public ArrayList<Integer> getDelPositions() {
        return delPositions;
    }

    public void setDelPositions(ArrayList<Integer> delPositions) {
        this.delPositions = delPositions;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public writeQueueMessage( byte type,int position, String ch) {
        this.position = position;
        this.ch = ch;
        this.type=type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }
}
