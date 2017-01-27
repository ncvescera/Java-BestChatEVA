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
    
    private String nome;
    
    public Handler(Socket socket){
        this.socket = socket;
        this.nome = "";
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
            writer.println("Inserisci un Nickname:");
            
            nome = reader.readLine();
            
            PublicVars.nicks.add(nome);
            System.out.println(nome+"("+socket.getInetAddress()+") si è connesso");
            writer.println("Ora sei "+nome);
            
            for(PrintWriter elem:PublicVars.writers){
                elem.println(nome+" si è connesso ...");
            }
            
            PublicVars.writers.add(writer);
            
            String input = "";
            while(input != null){
                input = reader.readLine();
                
                if(input != null){
                    for(PrintWriter elem:PublicVars.writers){
                        elem.println(nome+": "+input);
                    }
                }
            }
            
        } catch(IOException e){
            System.out.println("Da gestire");
        } finally{
            try{
                if(writer != null){
                    PublicVars.writers.remove(writer);
                    this.writer.close();
                }
                if(this.reader != null){
                    reader.close();
                }
                for(PrintWriter elem:PublicVars.writers){
                    elem.println(nome+" si è disconnesso");
                }
                System.out.println(nome+"("+socket.getInetAddress()+") si è disconnesso!");

                socket.close();

            } catch(IOException e){
                System.err.println("Impossibile chiudere il socket!");
            }
        }
    }
}
