import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by gol on 22.03.2017.
 */
public class UDPServer {
    //Port des Servers
    static final int SERVER_PORT = 10001;

    public static void main(String[] args) {
        try {
            //Erzeugen des Socket
            DatagramSocket socket = new DatagramSocket(SERVER_PORT);

            while (true) {
                //Erzeugen eines Puffers
                byte[] b = new byte[128];

                DatagramPacket packet = new DatagramPacket(b, b.length);
                System.out.println("Warten auf Daten");

                //Der Server verharrt in der Methode receive()
                // solange, bis er ein Packet zugesendet bekommt
                socket.receive(packet);

                //Daten aus Paket extrahieren und ausgeben
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Nachricht empfangen: " + message);

                //Begrüssungsnachricht in Paket verpacken
                b = ("Hallo " + message).getBytes();
                System.out.println("Sende Antwort: " + new String(b));

                //DatagramPaket erzeugen und darin die Antwort an den Sender
                //verpacken. Zudem muss in dem Paket die IP-Adresse und der
                //Port des Empfängers enthalten sein
                DatagramPacket response = new DatagramPacket(b, b.length, packet.getAddress(), packet.getPort());

                //Paket an Client senden
                socket.send(response);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
