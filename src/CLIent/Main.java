package CLIent;


/**
 *
 * @author ncvescera
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static boolean live = true;
    
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
        
        while(live){
            client.sendMessage(EasyInput.inputS(""));
        }
        //System.out.println("\033[31;1mHello\033[0m, \033[32;1;2mworld!\033[0m");//colora l'output
        
    }
    
}
