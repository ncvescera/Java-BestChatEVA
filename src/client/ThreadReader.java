/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author ncvescera
 */
public class ThreadReader extends Thread{
    private BufferedReader reader;
    private JTextArea text;
    private String nickname;
    private boolean live;
    
    public ThreadReader(BufferedReader reader, JTextArea text){
        this.reader = reader;
        this.text = text;
        this.nickname = "";
        this.live = true;
    }
    
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    
    private String replaceNick(String str){
        String nick = "";
        try{
            nick = str.substring(0,str.indexOf(":"));
            
            
        } catch(StringIndexOutOfBoundsException e){
            return str;
        }
        
        if(this.nickname.equals(nick))
                return "IO:"+str.substring(str.indexOf(":")+1);
            else
                return str;
    }
    
    public void kill(){
        this.live = false;
    }
    @Override
    public void run(){
        while(live){
            try{
                String in = replaceNick(reader.readLine());
                
                if(in != null){
                    text.setText(text.getText()+"\n"+in+"\n");
                }
            } catch(IOException e){
                System.out.println("Exit ...");
            }
        }
        
        try {
            this.reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
