/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLIent;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author ncvescera
 */
public class ThreadReader extends Thread{
    private BufferedReader reader;
    private boolean live;
    
    public ThreadReader(BufferedReader reader){
        this.reader = reader;
        this.live = true;
    }
    
    public void kill(){
        this.live = false;
    }
    @Override
    public void run(){
        while(live){
            try{
                String in = reader.readLine();
                
                if(in != null){
                    System.out.println(in);
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
