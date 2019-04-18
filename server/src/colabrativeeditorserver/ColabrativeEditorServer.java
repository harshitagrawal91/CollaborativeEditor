/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colabrativeeditorserver;

import constants.GlobalConstants;


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
        
            int port= GlobalConstants.getConfiguration().getPort();
            Server s=Server.getInstance(port);
            s.start();
    }
    
}
