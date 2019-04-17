/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import EditorUi.EditorClient;
import EditorUi.EditorWindowFrame;
import EditorUi.Main;
import clientObjects.writeQueueMessage;
import constants.GlobalConstants;

/**
 * @author akshaysehgal
 *
 */
class mainFunctionTest {
	
	@Test
	void test() {
		Random rGen = new Random();
	    Random rObj = new Random(); 
		int number = rGen.nextInt(100);
    	int objSelection = rObj.nextInt(2);
		try {
			try {
	        	EditorClient ec = new EditorClient(7001);
	             ec.connectToServer();
	        } catch (IOException ex) {
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	        }
			try {
	            TimeUnit.SECONDS.sleep(2);
	            }
	            catch(Exception e) {
	            	e.getMessage();
	            }
			EditorWindowFrame editWin1 = new EditorWindowFrame();
			editWin1.setVisible(true);
            GlobalConstants.editWin=editWin1;
            editWin1.setInitial();                    //Is it right to use get and get and increment in the same scope????????
            
            try {
            	EditorClient ec = new EditorClient(7001);
                 ec.connectToServer();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                TimeUnit.SECONDS.sleep(2);
                }
                catch(Exception e) {
                	e.getMessage();
                }
			EditorWindowFrame editWin2 = new EditorWindowFrame();
			editWin2.setVisible(true);
            GlobalConstants.editWin=editWin2;
            editWin2.setInitial();
           
            
            for(int i=0; i<100; i++) {
            	
            	if (objSelection == 1) {
            		int len = editWin1.writingArea.getDocument().getLength();
            		editWin1.writingArea.setCaretPosition(len);
            		editWin1.writingArea.append(Integer.toString(number));
            		writeQueueMessage msg = new writeQueueMessage(GlobalConstants.messageType.INSERT.getValue(), len, Integer.toString(number));
        			GlobalConstants.writer.getMessage().add(msg);
            	}else if(objSelection == 0) {
            		int len = editWin2.writingArea.getDocument().getLength();
            		editWin2.writingArea.setCaretPosition(len);
            		editWin2.writingArea.append(Integer.toString(number));
            		writeQueueMessage msg = new writeQueueMessage(GlobalConstants.messageType.INSERT.getValue(), len, Integer.toString(number));
        			GlobalConstants.writer.getMessage().add(msg);
            	}
            	number = rGen.nextInt(100);
            	objSelection = rObj.nextInt(2);
            	System.out.println("obj" + objSelection);
            }
            
            assertEquals(editWin1.writingArea.getText(),editWin2.writingArea.getText());
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       

        //Assertions.assertEquals(expected, actual);
        
	}

}


