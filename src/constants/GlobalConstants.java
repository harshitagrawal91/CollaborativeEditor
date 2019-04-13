/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import objects.ClientInfo;
import objects.conf;

/**
 *
 * @author harsh
 */
public class GlobalConstants {
    public static conf configuration;
    public static HashMap <Long,ArrayList<ClientInfo>> clientMap=new HashMap<>();
    public static AtomicInteger clientId=new AtomicInteger();
    public static StringBuffer documentName=new StringBuffer("newDocument");
    public static StringBuffer text=new StringBuffer("harsh");
    

    public static HashMap<Long, ArrayList<ClientInfo>> getClientMap() {
        return clientMap;
    }

    public static void setClientMap(HashMap<Long,ArrayList<ClientInfo>> clientMap) {
        GlobalConstants.clientMap = clientMap;
    }

    public static conf getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(conf configuration) {
        GlobalConstants.configuration = configuration;
        
    }
}
