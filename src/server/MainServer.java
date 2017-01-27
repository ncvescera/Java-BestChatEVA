/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ncvescera
 */
public class MainServer {
    
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PublicVars.ServerPort); //il server ascolta la porta
        System.out.println("Il server è in ascolto sulla porta: "+PublicVars.ServerPort);
        
        Socket socket = null;
        
        try{
            while(true){
                socket = listener.accept();
                //System.out.println();
                new Handler(socket).start(); //avvio il thread che gesità il singolo user
            }
        } catch(IOException e){
            System.out.println("E' successo qualcosa");
        } finally{
            listener.close();
        }
        
    }
    
}
