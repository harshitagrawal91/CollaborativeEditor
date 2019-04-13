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
  Identifier position;
  char character;

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
