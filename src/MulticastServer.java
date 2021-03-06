import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by gol on 22.03.2017.
 */
public class MulticastServer {
    //IP-Adressierung der Gruppe
    public static final String GRUPPEN_ADRESSE = "224.5.6.7";

    //Port der Gruppe
    public static final int GRUPPEN_PORT = 6789;

    public static void main(String[] args) {
        try {

            //Erzeugen eines Puffers zum Empfang von Anfragen
            byte[] buffer = new byte[128];

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            System.out.println("Warten auf Daten");

            InetAddress address = InetAddress.getByName(GRUPPEN_ADRESSE);

            //Erzeugen eines Socket
            MulticastSocket socket = new MulticastSocket(GRUPPEN_PORT);
            System.out.println("MulticastSocket erzeugt ...");

            //Beitritt zur Multicast-Gruppe
            socket.joinGroup(address);
            System.out.println("Der Gruppe beigetreten: " + GRUPPEN_ADRESSE + "/" + GRUPPEN_PORT);

            while (true) {

                System.out.println("Warten auf Daten ...");

                //Empfang einer Anfrage
                socket.receive(packet);

                //Daten aus Paket extrahieren und ausgeben
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Nachricht empfangen: " + message + " von " + packet.getAddress());

                //Beantworten der Anfrage
                message = "Hallo Client!";
                DatagramPacket response = new DatagramPacket(message.getBytes(), message.length(), packet.getAddress(), packet.getPort());

                System.out.println("Sende Antwort an Client ...");
                socket.send(response);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
