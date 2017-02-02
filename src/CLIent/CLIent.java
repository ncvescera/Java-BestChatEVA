/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLIent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author ncvescera
 */
public class CLIent {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean firstMessage;
    private ThreadReader threader;
    
    public CLIent(String ip, int port){
        this.firstMessage = true;
        
        try{
            socket = new Socket(ip,port);

            in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            out = new PrintWriter(
                    socket.getOutputStream(),true);

        } catch(IOException e){
            System.err.println(e);
        }
        
        this.threader = new ThreadReader(in);
        this.threader.start();
    }
    
    public void sendMessage(String message){
        if(firstMessage){
            threader.setNickname(message);
            firstMessage = false;
        }
        
        if(out != null && !message.equals("")){
            out.println(message);
        }
    }
}
