package server;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gestisce l'inoltro dei messaggi tra client
 * @author ncvescera e smpiccini
 */
public class Handler extends Thread {

    private Socket socket;
    private ObjectInputStream reader;

    private User user;
    
    /**
     * Costruttore
     * @param socket socket per la connessione 
     */
    public Handler(Socket socket) {
        this.socket = socket;
        this.user = new User();
    }

    @Override
    /**
     * Gestisce l'inoltro dei messaggi tra client
     */
    public void run() {
        try {
            //creo il buffer di lettura
            reader = new ObjectInputStream(socket.getInputStream());
            //creo il buffer di scrittura
            user.setStreamOut(new ObjectOutputStream(socket.getOutputStream()));

            user.sendMessage("START CHAT ...");
            user.sendMessage("Inserisci un Nickname:");

            user.setNick(recieveMessage());

            System.out.println(user.getNick() + "(" + socket.getInetAddress() + ") si è connesso");
            user.sendMessage("Ora sei " + user.getNick());

            for (User elem : MainServer.users) {
                elem.sendMessage(user.getNick() + " si è connesso ...");
            }

            MainServer.users.add(user);

            String input = "";
            while (input != null) {
                input = recieveMessage();

                if (input != null) {

                    if (input.equals(":file")) {
                        //invia comando :file
                        for (User elem : MainServer.users) {
                            if (elem != user) {
                                elem.sendMessage(input);
                            }
                        }
                        //invia nome file
                        String filename = recieveMessage();
                        System.out.println("Nome file: "+filename);
                        for (User elem : MainServer.users) {
                            if (elem != user) {
                                elem.sendMessage(filename);
                            }
                        }
                        //invia file
                        byte[] file = recieveFile();
                        for (User elem : MainServer.users) {
                            if (elem != user) {
                                elem.sendFile(file);
                            }
                        }

                    } else {

                        for (User elem : MainServer.users) {
                            if (elem != user) {
                                elem.sendMessage(elem.getColore() + elem.getNick() + ": " + elem.getColoreDef() + input);
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        } finally {
            try {
                user.closeStreams();
                if (this.reader != null) {
                    reader.close();
                }
                for (User elem : MainServer.users) {
                    elem.sendMessage(elem.getColore() + elem.getNick() + ": " + elem.getColoreDef() + " si è disconnesso");
                }
                System.out.println(user.getNick() + "(" + socket.getInetAddress() + ") si è disconnesso!");
                MainServer.users.remove(user);
                socket.close();

            } catch (IOException e) {
                System.err.println(e);
                System.exit(1);
            }
        }
    }
    
    /**
     * Riceve il messaggio dal client
     * @return Messaggio del client
     */
    private String recieveMessage() {
        byte[] b = null;
        try {
            b = (byte[]) reader.readObject();
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
            System.exit(1);
        }
        String message = "";
        if (b != null) {
            message = new String(b);
        }
        return message;
    }
    
    /**
     * Riceve il file dal client
     * @return Array di byte ricevuto dal client
     */
    private byte[] recieveFile() {
        byte[] b = null;
        try {
            b = (byte[]) reader.readObject();
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
            System.exit(1);
        }
        return b;
    }
}
