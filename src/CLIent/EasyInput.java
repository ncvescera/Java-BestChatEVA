package CLIent;

import java.io.*;
import javafx.geometry.Point2D;

public class EasyInput {
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
    
    public static Point2D inputP2D(String arg){
        double inputX = inputD(arg+"\nX: ");
        double inputY = inputD("Y: ");
        
        return new Point2D(inputX,inputY);
    }
}