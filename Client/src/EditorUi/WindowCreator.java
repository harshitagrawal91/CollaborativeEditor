package EditorUi;



import constants.GlobalConstants;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kunal
 */
public class WindowCreator extends Thread {
	
	Boolean windowSelector;
	
	public WindowCreator(boolean val) {
        this.windowSelector = val;
    }

    @Override
    public void run() {
       if(windowSelector) {
                MainWindowFrame mainWin = new MainWindowFrame();
                mainWin.invoke();
       }
                else if (!windowSelector) {
                try {
                	EditorWindowFrame editWin = new EditorWindowFrame();
                    editWin.setVisible(true);
                    GlobalConstants.editWin=editWin;
                    editWin.setInitial();                    //Is it right to use get and get and increment in the same scope????????
                    //new EditorServer().start();
                } catch (IOException ex) {
                    Logger.getLogger(WindowCreator.class.getName()).log(Level.SEVERE, null, ex);
                }
              
        }
    }
    windowType wType;
    private static volatile AtomicLong uniqueID = new AtomicLong(0);
}
enum windowType {

    MAIN, EDITOR
}
