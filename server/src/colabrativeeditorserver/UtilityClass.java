/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colabrativeeditorserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import objects.conf;

/**
 *
 * @author harsh
 */
public class UtilityClass {
    public static conf readServerInfoConfig() {
        conf configuration =new conf();
        File f = new File("ServerInfo.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                String s[] = line.split("\\s");
                configuration.setPort(Integer.parseInt(s[0]));
            }
        } catch (FileNotFoundException fe) {
            System.out.print("peerinfo config file not found" + fe);
        } catch (IOException e) {
            System.out.print("IO exception while reading peerinfo config" + e);
        }
        return configuration;
    }
}
