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
 * @author dell
 */
public class WindowCreator extends Thread {

    public WindowCreator(windowType wType) {
        this.wType = wType;
    }

    @Override
    public void run() {
        switch (wType) {
            case MAIN:
                MainWindowFrame mainWin = new MainWindowFrame();
                mainWin.invoke();
                break;
            case EDITOR:
                EditorWindowFrame editWin = null;
                try {
                    editWin = new EditorWindowFrame();
                    
                    editWin.invoke();
                    GlobalConstants.editWin=editWin;
                    editWin.initialize();
                    //Is it right to use get and get and increment in the same scope????????
                    //new EditorServer().start();
                } catch (IOException ex) {
                    Logger.getLogger(WindowCreator.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

        }
    }
    windowType wType;
    private static volatile AtomicLong uniqueID = new AtomicLong(0);
}

enum windowType {

    MAIN, EDITOR
}
