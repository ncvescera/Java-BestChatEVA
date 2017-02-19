package server;

import java.io.*;

/**
 * Gestisce gli stream degli utenti connessi
 * @author smpiccini
 */
public class User {

    private ObjectOutputStream streamOut;
    private String nick;
    private String colore;
    private String coloreDef;
    
    /**
     * Instanzia l'array di colori e ne sceglie uno a caso
     */
    public User() {
        nick = null;
        streamOut = null;
        String[] colors = {"\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m", "\u001B[37m"};
        colore = colors[(int) (Math.random() * colors.length)];
        coloreDef = "\u001B[0m";
    }
    
    /**
     * Invia il messaggio al client
     * @param message Messaggio da inviare
     */
    public void sendMessage(String message) {
        byte[] b = null;
        if (!message.equals("")) {
            b = message.getBytes();
            try {
                streamOut.writeObject(b);
                //streamOut.flush();
            } catch (IOException ex) {
                System.err.println(ex);
                System.exit(1);
            }
        }

    }
    
    /**
     * Invia il file al client
     * @param byteFile Array di byte che contiene un file
     */
    public void sendFile(byte[] byteFile) {
        try {
            streamOut.writeObject(byteFile);
            //streamOut.flush();
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
    }
    
    /**
     * Chiude lo stream in output del client
     */
    public void closeStreams() {
        if (streamOut != null) {
            try {
                streamOut.close();
            } catch (IOException ex) {
                System.err.println(ex);
                System.exit(1);
            }
        }
    }

    //GETTERS
    /**
     * Restituisce il nick dell'utente
     * @return nickname
     */
    public String getNick() {
        return nick;
    }
    
    /**
     * Restituisce il clolore dell'utente
     * @return colore 
     */
    public String getColore() {
        return colore;
    }
    
    /**
     * Restituisce il colore predefinito
     * @return colore di default
     */
    public String getColoreDef() {
        return coloreDef;
    }

    //SETTERS
    /**
     * Sostituisce l'uotputStream attuale con quello che gli viene passato
     * @param streamOut Nuovo outputStream
     */
    public void setStreamOut(ObjectOutputStream streamOut) {
        this.streamOut = streamOut;
    }
    
    /**
     * Sostituisce il nick attuale con quello che gli viene passato
     * @param nick Nuovo nick
     */
    public void setNick(String nick) {
        this.nick = nick;
    }
    
    /**
     * Sostituisce il colore attuale con quello che gli viene passato
     * @param colore Nuovo colore
     */
    public void setColore(String colore) {
        this.colore = colore;
    }

}
