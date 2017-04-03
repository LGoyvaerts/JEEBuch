/**
 * Created by gol on 21.03.2017.
 */

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
    //Port der Serveranwendung
    public static final int SERVER_PORT = 10001;

    //Rechnername des Servers
    public static final String SERVER_HOSTNAME = "localhost";

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);

            //Erzeugen des Socket und Aufbau der Verbindung
            Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);

            System.out.println("Verbunden mit Server: " + socket.getRemoteSocketAddress());
            String nachricht;
            System.out.print("Nachricht: ");
            nachricht = in.nextLine();
            System.out.println("Sende Nachricht \"" + nachricht + "\" mit Laenge " + nachricht.length());

            //Senden der Nachricht über einen Stream
            socket.getOutputStream().write(nachricht.getBytes());

            //Beenden der Kommunikationsverbindung
            socket.close();
        } catch (UnknownHostException e) {
            //Wenn Rechnername nicht bekannt ist ...
            System.out.println("Rechnername unbekannt:\n" + e.getMessage());
        } catch (IOException e) {
            //Wenn die Kommunikation fehlschlägt
            System.out.println("Fehler während der Kommunikation:\n" + e.getMessage());
        }
    }
}
