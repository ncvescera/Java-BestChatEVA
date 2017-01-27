/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author ncvescera
 */
public class PublicVars {
    
    public static ArrayList<PrintWriter> writers = new ArrayList();
    public static ArrayList<String> nicks = new ArrayList(); //arrey contenente i nicknames
    public static int ServerPort = 2000; //Unassigned TCP port
}
