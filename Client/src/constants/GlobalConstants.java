/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

import EditorUi.EditorWindowFrame;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author harsh
 */
public class GlobalConstants {

    public volatile static AtomicInteger clientId=new AtomicInteger();
    public static StringBuffer documentName = new StringBuffer("newDocument");
    public static StringBuffer text = new StringBuffer("harsh");
    public volatile static EditorWindowFrame editWin;

}
