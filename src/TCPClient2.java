/**
 * Created by gol on 21.03.2017.
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient2 {
    //Port der Serveranwendung
    public static final int SERVER_PORT = 10001;

    //Rechnername des Servers
    public static final String SERVER_HOSTNAME = "localhost";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Aufruf: java TCPClient2 <Client-Name>");

            System.exit(1);
        }
        try {
            Scanner in = new Scanner(System.in);

            //Erzeugen des Socket und Aufbau der Verbindung
            Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);


            System.out.println("Verbunden mit Server: " + socket.getRemoteSocketAddress());
            System.out.println("Client\"" + args[0] + "\"meldet sich am Server an");

            //Senden der Nachricht über einen Stream
            socket.getOutputStream().write(args[0].getBytes());

            //Puffer erzeugen und auf Begrüssung warten
            byte[] b = new byte[128];

            InputStream stream = socket.getInputStream();

            while (stream.available() == 0) ;

            //Begrüssung lesen und ausgeben
            stream.read(b);
            System.out.println("Nachricht vom Server ist: " + new String(b));

        } catch (UnknownHostException e) {
            //Wenn Rechnername nicht bekannt ist ...
            System.out.println("Rechnername unbekannt:\n" + e.getMessage());
        } catch (IOException e) {
            //Wenn die Kommunikation fehlschlägt
            System.out.println("Fehler während der Kommunikation:\n" + e.getMessage());
        }
    }
}
