/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JTextArea;
import java.lang.StringIndexOutOfBoundsException;

/**
 *
 * @author ncvescera
 */
public class ThreadReader extends Thread{
    private BufferedReader reader;
    private JTextArea text;
    private String nickname;
    
    public ThreadReader(BufferedReader reader, JTextArea text){
        this.reader = reader;
        this.text = text;
        this.nickname = "";
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
    
    @Override
    public void run(){
        while(true){
            try{
                String in = replaceNick(reader.readLine());
                
                if(in != null){
                    text.setText(text.getText()+"\n"+in+"\n");
                }
            } catch(IOException e){
                System.err.println(e.getCause());
            }
        }
    }
}
