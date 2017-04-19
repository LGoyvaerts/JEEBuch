package messageServer; /**
 * Created by gol on 21.03.2017.
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

public class Server extends Thread {
    //Port der Serveranwendung
    public static Set<Socket> clients = new HashSet<>();
    public static final int SERVER_PORT = 10002;
    public static final String SERVER_HOSTNAME = "localhost";


    //Name dieses Threads. Er wird dadurch markiert, welche
    //Ausgaben auf der Konsole von diesem Thread stammen.
    private static final String name = "MainThread";

    public static void main(String[] args) throws Exception {
        try {
            //Erzeugen des Socket/binden an Port/Wartestellung
            ServerSocket socket = new ServerSocket(SERVER_PORT, 10);
            String nachrichtOpponent = "0";

            while (true) {

                //Im Aufruf der Methode accept() verharrt die
                //Server-Anwendung solange, bis eine Verbindungsanforderung
                //eines Client eingegangen ist. Ist dies der Fall, so wird
                //die Anforderung akzeptiert
                Socket client = socket.accept();
                clients.add(client);

                sleep(1000);

                /*//Erzeugen eines Puffers
                byte[] b = new byte[128];

                //Datenstrom zum Lesen verwenden
                InputStream streamClient = client.getInputStream();

                //Sind Daten verfügbar?
                while (streamClient.available() == 0) {
                }

                //Ankommende Daten lesen und ausgeben
                while (streamClient.read(b) != -1) {
                    nachrichtOpponent = new String(b);
                    client.getOutputStream().write(nachrichtOpponent.getBytes());
                    System.out.println("\"" + nachrichtOpponent + "\"");
                }*/

                ////////////////


                new WorkerThread(client, nachrichtOpponent).start();


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
        private String nachrichtClient;

        public WorkerThread(Socket client, String nachrichtPing) {
            this.client = client;
            this.nachrichtClient = nachrichtPing;
        }

        public void run() {
            try {

                //Erzeugen eines Puffers und Einlesen des Namens
                byte[] b = new byte[128];
                InputStream input = client.getInputStream();

                //Warten auf Daten
                while (input.available() == 0) ;

                while (input.read(b) != -1) {
                    String message = new String(b);
                    client.getOutputStream().write(nachrichtClient.getBytes());
                    Server.print(client.getInetAddress().getHostName() + ": \"" + message + "\"");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
