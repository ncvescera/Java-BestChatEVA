package CLIent;

import java.io.*;

/**
 * Thread per la gestione dei messaggi in arrivo dal server
 * @author ncvescera e smpiccini
 */
public class ThreadReader extends Thread {

    private ObjectInputStream reader;
    private boolean live;
    
    /**
     * Costruttore
     * @param reader Stream in input 
     */
    public ThreadReader(ObjectInputStream reader) {
        this.reader = reader;
        this.live = true;
    }
    
    
    @Override
    /**
     * Gestisce i messaggi in arrivo dal server
     */
    public void run() {
        byte[] b = null;
        String in = "";
        while (live) {
            try {
                b = (byte[]) reader.readObject();
                in = new String(b);

                if (!in.equals("")) {
                    if (in.equals(":file")) {
                        b = (byte[]) reader.readObject();
                        String filename = new String(b);
                        System.out.println("FILE IN ARRIVO:\n"
                                + " Nome file: " + filename);
                        recieveFile(filename);
                    } else {

                        System.out.println(in);
                    }
                }
            } catch (IOException e) {
                System.err.println(e);
                System.exit(1);
            } catch (ClassNotFoundException ex) {
                System.err.println(ex);
                System.exit(1);
            }
        }

        try {
            this.reader.close();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }
    
    /**
     * Salva il file in arrivo dal server
     * @param filename Nome con cui verrà salvato il file
     */
    public void recieveFile(String filename) {
        BufferedOutputStream bos = null;
        byte[] b = null;
        try {
            b = (byte[]) reader.readObject();
            bos = new BufferedOutputStream(new FileOutputStream(filename));
            
            System.out.println("FILE IN ARRIVO:\n"
                                + " Nome file: " + filename
                                + " Dimensione: "+ b.length+ " Bytes.");
            
            bos.write(b,0,b.length);
            //bos.flush();
        } catch (IOException ex) {
            System.err.println(ex);
                System.exit(1);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
                System.exit(1);
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException ex) {
                System.err.println(ex);
                System.exit(1);
            }
        }
    }
}
