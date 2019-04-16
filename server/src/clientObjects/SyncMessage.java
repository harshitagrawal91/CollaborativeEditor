/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientObjects;

import java.io.Serializable;

/**
 *
 * @author harsh
 */
public class SyncMessage implements Serializable {

    Identifier position;
    char character;
    int actualPosition;
    byte type;
    boolean update = false;
    int updateWindowSiteId;

    public int getUpdateWindowSiteId() {
        return updateWindowSiteId;
    }

    public void setUpdateWindowSiteId(int updateWindowSiteId) {
        this.updateWindowSiteId = updateWindowSiteId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public int getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(int actualPosition) {
        this.actualPosition = actualPosition;
    }

    public Identifier getPosition() {
        return position;
    }

    public void setPosition(Identifier position) {
        this.position = position;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

}
