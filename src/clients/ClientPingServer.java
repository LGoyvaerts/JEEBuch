package clients;

import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by gol on 03.04.2017.
 */
public class ClientPingServer extends Thread {
    public static final int EIGENER_PORT = 10000;
    public static final int SERVER_PORT = 10002;
    public static final String SERVER_HOSTNAME = "localhost";

    public static void main(String[] args) throws Exception {
        System.out.println("Das Spiel beginnt mit einem Aufschlag");
        while (true) {
            Scanner in = new Scanner(System.in);

            //Erzeugen des Socket und Aufbau der Verbindung
            Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);

            sleep(1000);
            String nachricht = "Ping";

            socket.getOutputStream().write(nachricht.getBytes());

            InputStream stream = socket.getInputStream();

            while (stream.available() == 0) ;

            stream.read();

            //Beenden der Kommunikationsverbindung
            socket.close();


        }
    }
}
