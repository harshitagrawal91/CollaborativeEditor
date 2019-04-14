/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

import EditorUi.EditorWindowFrame;
import EditorUi.Writer;
import EditorUi.Listener;
import clientObjects.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author harsh
 */
public class GlobalConstants {

    public volatile static AtomicInteger clientId = new AtomicInteger();
    public static StringBuffer documentName = new StringBuffer("newDocument");
    public static StringBuffer text = new StringBuffer("harsh");
    public volatile static EditorWindowFrame editWin;
    //public static List<String> positionList = Collections.synchronizedList(new ArrayList<String>()); 
    public static CopyOnWriteArrayList<Identifier> positionList = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<Double> doublepositionList = new CopyOnWriteArrayList<>();
    public static Writer writer;
    public static Listener listener;

}
