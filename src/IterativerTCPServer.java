/**
 * Created by gol on 21.03.2017.
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class IterativerTCPServer {
    //Port der Serveranwendung
    public static final int SERVER_PORT = 10001;

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

                //Ausgabe der Informationen über den Client
                System.out.println("\nVerbunden mit Rechner: " + client.getInetAddress().getHostName() + " Port: " + client.getPort());

                //Erzeugen eines Puffers
                byte[] b = new byte[128];

                //Datenstrom zum Lesen verwenden
                InputStream stream = client.getInputStream();

                //Sind Daten verfügbar?
                while (stream.available() == 0) {
                }

                //Ankommende Daten lesen und ausgeben
                while (stream.read(b) != -1) {
                    System.out.println("Nachricht empfangen: " + new String(b));
                }

                //Verbindung beenden
                client.close();


                System.out.println("Der Client wurde bedient\n");
            }
        } catch (UnknownHostException e) {
            //Wenn Rechnername nicht bekannt ist ...
            System.out.println("Rechnername unbekannt:\n" + e.getMessage());
        } catch (IOException e) {
            //Wenn Kommunikation fehlschlägt ...
            System.out.println("Fehler während der Kommunikation:\n" + e.getMessage());
        }
    }
}
