/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientObjects;

/**
 *
 * @author dell
 */
public class writeQueueMessage {
    int position;
    String ch;

    public writeQueueMessage(int position, String ch) {
        this.position = position;
        this.ch = ch;
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
