package CLIent;

import java.awt.*;
import javax.swing.*;

/**
 * Classe di avvio del CLIent
 * @author ncvescera e smpiccini
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String host;
        int port;
        String message = "";
        byte[] b = null;

        JFileChooser chooser;
        Frame f = new Frame();

        if (args.length > 0) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } else {
            host = "localhost";
            port = 2000;
        }

        CLIent client = new CLIent(host, port);
        message = EasyInput.inputS("");
        while (!message.equals(":exit")) {

            message = message.replace(":)", "\u263A");
            b = client.convertMessage(message);

            if (message.equals(":file")) {
                chooser = new JFileChooser();
                f.setVisible(true);
                int n = chooser.showOpenDialog(f);
                if (n == JFileChooser.APPROVE_OPTION) {
                    client.send(b);		//invia comando :file

                    b = client.convertMessage(chooser.getSelectedFile().getName());
                    client.send(b);		//invia nome file

                    b = client.convertFile(chooser.getSelectedFile());
                    client.send(b);		//invia file
                }
                f.setVisible(false);
            } else {
                client.send(b);		//invia messaggio
            }
            message = EasyInput.inputS("");
        }
        client.close();
    }

}
