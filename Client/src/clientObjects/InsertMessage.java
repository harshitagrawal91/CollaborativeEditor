/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientObjects;

import java.io.Serializable;

/**
 *
 * @author dell
 */
public class InsertMessage implements Serializable {
  Identifier position;
  char character;
  int actualPosition;

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
