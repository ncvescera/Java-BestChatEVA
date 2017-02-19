package CLIent;

import java.io.*;
import java.net.Socket;

/**
 * Classe che gestisce gli stream di input e output del client
 * @author ncvescera e smpiccini
 */
public class CLIent {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ThreadReader threader;
    
    /**
     * Metodo costruttore che instanzia il socket e gli stream di input e output
     * @param ip indirizzo ip del server
     * @param port porta sulla quale il server è in ascolto
     */
    public CLIent(String ip, int port) {

        try {
            socket = new Socket(ip, port);

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        this.threader = new ThreadReader(in);

        this.threader.start();
    }
    
    /**
     * Legge un file e salva il contenuto in un array di byte
     * @param f File in input
     * @return Array di byte che sarà poi inviato al server
     */
    public byte[] convertFile(File f) {
        BufferedInputStream bis = null;
        byte[] b = null;
        try {
            b = new byte[(int) f.length()];
            bis = new BufferedInputStream(new FileInputStream(f));
            bis.read(b, 0, b.length);
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
        return b;
    }
    
    /**
     * Converte il messaggio in un array di byte
     * @param message Messaggio del client
     * @return Array di byte che sarà poi inviato al server
     */
    public byte[] convertMessage(String message) {
        byte[] b = null;
        if (!message.equals("")) {
            b = message.getBytes();
        }
        return b;
    }

    /**
     * Invia l'array di byte al server
     * @param b Array di byte precedentemente creato
     */
    public void send(byte[] b) {

        if (out != null && b != null) {
            try {

                out.writeObject(b);
                //out.flush();
            } catch (IOException ex) {
                System.err.println(ex);
                System.exit(1);
            }
        }

    }
    
    /**
     * Chiude la connessione tra client e server
     */
    public void close() {
        try {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
        this.threader.stop();
    }

}
