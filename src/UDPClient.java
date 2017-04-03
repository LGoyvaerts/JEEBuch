import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by gol on 22.03.2017.
 */
public class UDPClient {
    //Rechnername des Servers
    static final String SERVER_NAME = "localhost";

    //Port des Servers
    static final int SERVER_PORT = 10001;

    public static void main(String[] args) {
        try {
            //Erzeugen eines Socket
            DatagramSocket socket = new DatagramSocket();

            //Name in Paket verpacken
            byte[] name = "Lorris Goyvaerts".getBytes();
            DatagramPacket packet = new DatagramPacket(name, name.length, InetAddress.getByName(SERVER_NAME), SERVER_PORT);

            //Paket an Server senden
            socket.send(packet);

            //Puffer für Begrüssungsnachricht erzeugen
            byte[] b = new byte[128];
            packet.setData(b);
            packet.setLength(128);

            //Timeout auf 5 Sekunden setzen
            socket.setSoTimeout(5000);

            System.out.println("Warten auf eine Antwort von Server ...");

            //Paket empfangen
            socket.receive(packet);

            //Begrüssung extrahieren und anzeigen
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Nachricht empfangen: " + message);

            //Socket schliessen
            socket.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
