/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JTextArea;

/**
 *
 * @author ncvescera
 */
public class ThreadReader extends Thread{
    private BufferedReader reader;
    private JTextArea text;
    
    public ThreadReader(BufferedReader reader, JTextArea text){
        this.reader = reader;
        this.text = text;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                String in = reader.readLine();
                if(in != null){
                    text.setText(text.getText()+"\n"+in+"\n");
                }
            } catch(IOException e){
                System.err.println(e.getCause());
            }
        }
    }
}
