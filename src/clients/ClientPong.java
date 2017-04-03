package clients;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by gol on 03.04.2017.
 */
public class ClientPong extends Thread {
    public static final int EIGENER_PORT = 10001;
    public static final int GEGENSPIELER_PORT = 10000;
    public static final String SERVER_HOSTNAME = "localhost";

    public static void main(String[] args) throws Exception {
        System.out.println("Warte auf Aufschlag");
        while (true) {
            Scanner in = new Scanner(System.in);

            ServerSocket serverSocket = new ServerSocket(EIGENER_PORT, 10);

            Socket client = serverSocket.accept();

            //Erzeugen eines Puffers
            byte[] b = new byte[128];

            //Datenstrom zum Lesen verwenden
            InputStream stream = client.getInputStream();

            //Sind Daten verfügbar?
            while (stream.available() == 0) {
            }

            //Ankommende Daten lesen und ausgeben
            while (stream.read(b) != -1) {
                System.out.println("Client Pong: \"" + new String(b) + "\"");
            }

            //Verbindung beenden
            client.close();

            serverSocket.close();

            //Erzeugen des Socket und Aufbau der Verbindung
            Socket socket = new Socket(SERVER_HOSTNAME, GEGENSPIELER_PORT);

            sleep(1000);

            String nachricht = "Pong";

            //Senden der Nachricht über einen Stream
            System.out.println("Client Ping: \"" + nachricht + "\"");
            socket.getOutputStream().write(nachricht.getBytes());

            //Beenden der Kommunikationsverbindung
            socket.close();


        }
    }
}
