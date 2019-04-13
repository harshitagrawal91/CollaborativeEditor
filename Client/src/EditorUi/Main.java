package EditorUi;



import java.io.IOException;
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
public class Main {
    public static void main(String args[])
    {
        System.out.println("Make Client connection");
        WindowCreator EditorWindow = new WindowCreator(windowType.EDITOR);
         EditorWindow.start();
          EditorClient ec;
        try {
            ec = new EditorClient(7001);
             ec.connectToServer();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
               
//        if(args.length>0){
//            try {
////                EditorClient ec=new EditorClient(Integer.parseInt(args[0]));
////                ec.connectToServer();
//            } catch (IOException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
        
//                }
        

        
        
       
    }
}
