/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colabrativeeditorserver;

import constants.GlobalConstants;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author harsh
 */
public class ColabrativeEditorServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GlobalConstants.setConfiguration(UtilityClass.readServerInfoConfig());
        try {
            Server s=new Server(GlobalConstants.getConfiguration().getPort());
            s.start();
            int a=10;
        } catch (IOException ex) {
//            Logger.getLogger(ColabrativeEditorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
