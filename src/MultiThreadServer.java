/**
 * Created by gol on 21.03.2017.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadServer {
    //Port der Serveranwendung
    public static List<Socket> clients = new ArrayList<>();
    public static final int SERVER_PORT = 10001;

    //Name dieses Threads. Er wird dadurch markiert, welche
    //Ausgaben auf der Konsole von diesem Thread stammen.
    private static final String name = "MainThread";

    public static void main(String[] args) {
        try {
            //Erzeugen des Socket/binden an Port/Wartestellung
            ServerSocket socket = new ServerSocket(SERVER_PORT, 10);

            while (true) {
                //Ab hier ist der Server "scharf" geschaltet und
                // wartet auf Verbindungen von Clients
                System.out.println("Warten auf Verbindungen ...");

                //Im Aufruf der Methode accept() verharrt die
                //Server-Anwendung solange, bis eine Verbindungsanforderung
                //eines Client eingegangen ist. Ist dies der Fall, so wird
                //die Anforderung akzeptiert
                Socket client = socket.accept();
                clients.add(client);

                print(name + "\tVerbunden mit: " + client.getInetAddress().getHostName() + " Port: " + client.getPort());

                //Thread erzeugen, der Kommunikation mit Client übernimmt
                new WorkerThread(client).start();

            }
        } catch (UnknownHostException e) {
            //Wenn Rechnername nicht bekannt ist ...
            System.out.println("Rechnername unbekannt:\n" + e.getMessage());
        } catch (IOException e) {
            //Wenn Kommunikation fehlschlägt ...
            System.out.println("Fehler während der Kommunikation:\n" + e.getMessage());
        }
    }

    public static synchronized void print(String nachricht) {
        System.out.println(nachricht);
    }

    static class WorkerThread extends Thread {
        private Socket client;

        //Name dieses Threads
        private final String name = "WorkerThread";

        public WorkerThread(Socket client) {
            this.client = client;
        }

        public void run() {
            try {
                //Erzeugen eines Puffers und Einlesen des Namens
                byte[] b = new byte[128];
                InputStream input = client.getInputStream();

                //Warten auf Daten
                while (input.available() == 0) ;

                //Nachricht auslesen
                input.read(b);
                String clientName = new String(b);
                MultiThreadServer.print(name + ":\tName empfangen: " + clientName);

                //Zufällige Zeit warten (0-5 sec)
                sleep((long) (Math.random() * 5000));

                //Begrüssung senden
                for (Socket s : clients) {
                    OutputStream output = s.getOutputStream();
                    MultiThreadServer.print(name + ":\tSende Antwort an Client " + clientName);

                    byte[] antwort = ("Hallo " + clientName).getBytes();
                    output.write(antwort);

                    //Verbindung beenden
                    MultiThreadServer.print(name + ":\tClient erfolgreich bedient ...");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
