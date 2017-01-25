/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author ncvescera
 */
public class Handler extends Thread{
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    
    public Handler(Socket socket){
        this.socket = socket;
    }
    
    @Override
    public void run(){
        try{
            //creo il buffer di lettura
            reader = new BufferedReader(
                    new InputStreamReader(
                            this.socket.getInputStream()));
            
            //creo il buffer di scrittura
            writer = new PrintWriter(
                    this.socket.getOutputStream(), true);
            
            writer.println("START CHAT ...");
            
            PublicVars.writers.add(writer);
            
            while(true){
                String input = reader.readLine();
                
                if(input != null){
                    for(PrintWriter elem:PublicVars.writers){
                        elem.println("SERVER: "+input);
                    }
                }
            }
            
        } catch(IOException e){
            System.out.println("Da gestire");
        } finally{
            if(writer != null) PublicVars.writers.remove(writer);
            
            try{
                socket.close();
            } catch(IOException e){
                System.err.println("Impossibile chiudere il socket!");
            }
        }
    }
}
