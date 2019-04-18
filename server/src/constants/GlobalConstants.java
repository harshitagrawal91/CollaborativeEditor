/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import clientObjects.ClientInfo;
import clientObjects.Identifier;
import clientObjects.*;
import colabrativeeditorserver.BroadcasterThread;
import colabrativeeditorserver.MessageHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author harsh
 */
public class GlobalConstants {

    public static conf configuration;
    //  public static HashMap <Long,ArrayList<ClientInfo>> clientMap=new HashMap<>();
    public static ConcurrentHashMap<Integer, ClientInfo> clientList = new ConcurrentHashMap<Integer, ClientInfo>();
    public static AtomicInteger clientId = new AtomicInteger();
    public static StringBuffer documentName = new StringBuffer("newDocument1");
    public static StringBuffer text = new StringBuffer("");
    public static CopyOnWriteArrayList<Identifier> positionList = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<Double> doublepositionList = new CopyOnWriteArrayList<>();
    //public static BroadcasterThread broadcasterThread;
    public static MessageHandler messageHandler;
    public static ExecutorService broadcast = Executors.newFixedThreadPool(50);

    public static enum messageType {
        DELETE((byte) 0),
        INSERT((byte) 1),
        SELECTIONDELETE((byte) 2),
        UPDATENAME((byte)3);

        private final byte value;

        private messageType(byte val) {
            this.value = val;
        }

        public byte getValue() {
            return value;
        }
    }

    public static conf getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(conf configuration) {
        GlobalConstants.configuration = configuration;

    }
}
