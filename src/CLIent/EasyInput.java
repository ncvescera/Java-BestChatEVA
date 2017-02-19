package CLIent;

import java.io.*;
/**
 * Classe che permette di effettuare l'input da tastiera in modo semplice e veloce
 * @author ncvescera e smpiccini
 */
public class EasyInput {
    /**
     * Metodo per richiedere l'inserimento di una stringa
     * @param arg Il messaggio che dovrà essere mostrato a video
     * @return La stringa che l'utente ha inserito
     */
    public static String inputS(String arg){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader tastiera = new BufferedReader(input);

        System.out.print(arg);
        try{
                String letto = tastiera.readLine();
                return letto;
                //System.out.println(letto);

            }
            catch(Exception e){
                System.out.println("ERRORE");
                return "";
            }
        
    }
    
    /**
     * Metodo per richiedere l'inserimento di un numero intero
     * @param arg Il messaggio che dovrà essere mostrato a video
     * @return Il numero che ha inserito l'utente
     */
    public static int inputI(String arg){
        String input = inputS(arg);
        int re = 0;
        try{
            re = Integer.parseInt(input);   
        }
        catch(Exception e){
            System.err.println("Error! Unparsable value");   
        }
        return re;
    }
    
    /**
     * Metodo per richiedere l'inserimento di un numero con la virgola
     * @param arg Il messaggio che dovrà essere mostrato a video
     * @return Il numero che ha inserito l'utente
     */
    public static float inputF(String arg){
        String input = inputS(arg);
        float parse = 0;
        try{
            parse = Float.parseFloat(input);
        }
        catch (Exception e){
            System.err.println("Error! Unparsable value.");
        }
        return parse;
    }
    
    /**
     * Metodo per richiedere l'inserimento di un double
     * @param arg Il messaggio che dovrà essere mostrato a video
     * @return Il numero che ha inserito l'utente
     */
    public static double inputD(String arg){
        String input = inputS(arg);
        double parse = 0;
        try{
            parse = Double.parseDouble(input);
        }
        catch (Exception e){
            System.out.println("Error! Unparsable value.");
        }
        return parse;
    }
}