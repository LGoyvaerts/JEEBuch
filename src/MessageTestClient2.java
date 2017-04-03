import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.util.Scanner;

/**
 * Created by gol on 22.03.2017.
 */
public class MessageTestClient2 {
    //IP-Adresse der Gruppe
    public static final String GRUPPEN_ADRESSE = "224.5.6.7";

    //Port der Gruppe
    public static final int GRUPPEN_PORT = 6789;

    public static void main(String[] args) {
        try {
            while (true) {
                Scanner in = new Scanner(System.in);
                SocketAddress adresse = new InetSocketAddress(GRUPPEN_ADRESSE, GRUPPEN_PORT);
                byte[] message;
                System.out.print("Nachricht: ");
                message = in.nextLine().getBytes();

                //Verpacken der Anfrage in ein Paket
                DatagramPacket packet = new DatagramPacket(message, message.length, adresse);

                //Erzeugen eines Socket und Senden der Anfrage
                MulticastSocket socket = new MulticastSocket();
                socket.send(packet);

                //Erzeugen eines Puffers
                byte[] b = new byte[128];
                packet.setData(b);
                packet.setLength(b.length);

                //Empfang der Antwort
                socket.receive(packet);

                //Extrahieren der Antwort und Ausgabe der Informationen
                String response = new String(packet.getData(), 0, packet.getLength());

                System.out.println(response);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
