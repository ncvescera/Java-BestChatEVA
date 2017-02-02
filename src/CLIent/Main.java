/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLIent;


/**
 *
 * @author ncvescera
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        CLIent client = new CLIent("192.168.1.62",2000);
        
        while(true){
            client.sendMessage(EasyInput.inputS(""));
        }
        
    }
    
}
