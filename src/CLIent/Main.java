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
        String host;
        int port;
        
        if(args.length > 0){
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        else{
            host = "localhost";
            port = 2000;
        }
            
        CLIent client = new CLIent(host,port);
        
        while(true){
            client.sendMessage(EasyInput.inputS(""));
        }
        //System.out.println("\033[31;1mHello\033[0m, \033[32;1;2mworld!\033[0m");//colora l'output
        
    }
    
}
